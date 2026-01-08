package com.nhlstenden.groep3.digital_twin.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Map;

import com.nhlstenden.groep3.digital_twin.Entity.AiReview;
import com.nhlstenden.groep3.digital_twin.Repository.AiReviewRepository;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    private static final String UPLOAD_DIR = "src/main/resources/uploads";
    private static final String OLLAMA_URL = "http://ollama:11434/api/generate";

    private final AiReviewRepository aiReviewRepository;

    public FileUploadController(AiReviewRepository aiReviewRepository) {
        this.aiReviewRepository = aiReviewRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file received");
            }

            // Save file to disk
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // Convert image to Base64
            String base64Image = Base64.getEncoder()
                    .encodeToString(file.getBytes());

            String requestBody = """
            {
              "model": "llava",
              "prompt": "Describe what you see in this image in 20 words or less. Do not under any circumstances exceed 20 words.",
              "images": ["%s"],
              "stream": false
            }
            """.formatted(base64Image);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request =
                    new HttpEntity<>(requestBody, headers);

            // Call Ollama
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> aiResponse =
                    restTemplate.postForEntity(OLLAMA_URL, request, Map.class);

            // Save response and image to database
            AiReview aiReview = new AiReview();
            Object responseText = aiResponse.getBody().get("response");
            String reviewContent = responseText != null
                    ? responseText.toString()
                    : "No AI response received";
            aiReview.setReviewContent(reviewContent);
            aiReview.setImagePath(filePath.toAbsolutePath().toString());
            aiReviewRepository.save(aiReview);

            return ResponseEntity.ok(Map.of(
                    "filePath", filePath.toAbsolutePath().toString(),
                    "aiResponse", aiResponse.getBody()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Upload or AI processing failed: " + e.getMessage());
        }
    }
}
