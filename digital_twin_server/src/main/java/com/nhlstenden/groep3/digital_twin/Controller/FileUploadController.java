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

    private static final String UPLOAD_DIR = "/uploads";
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
              "prompt": "This is a screenshot of a very abstract version of a city. The coloured shapes are different types of buildings or places. Give a rating of 0-100 based on the liveability of the total plot. Each color has its own liveability-score. Don't go over each color on its own, but give a short analysis of the entire picture. Don't say anything about real-world applications or other stuff, only do what this prompt says to do. Don't say what the image is, the user already knows. Only mention types of buildings in your response that are included in the picture, don't mention types that are not in the picture. NEVER EXCEED 50 WORDS. If you do, rewrite your response so that it's less than 50 words. Start your reply with the overall score of the area.These are all the types of buildings or places, followed by their hex-color (which may be a bit darker on the screenshot) and their liveability-score: Vrijstaand huis : #F4A460 : 4, Rijtjeswoning : #FF7F50 : 6, Appartement : #ADD8E6 : 5, Bedrijfsgebouw : #A9A9A9 : 2, Park/groen : #32CD32 : 10, Wegen : #696969 : 8, Parkeerplaatsen : #D3D3D3 : 6, Parkeerplaatsen overdekt : #708090 : 10.",
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
            aiReview.setImagePath("/uploads/" + file.getOriginalFilename());

            aiReviewRepository.save(aiReview);

            String publicImageUrl = "/uploads/" + file.getOriginalFilename();

            return ResponseEntity.ok(Map.of(
                    "imageUrl", publicImageUrl,
                    "aiResponse", aiResponse.getBody()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("Upload or AI processing failed: " + e.getMessage());
        }
    }

    @GetMapping("/ai-reviews")
    public ResponseEntity<?> getAiReviews() {
        return ResponseEntity.ok(aiReviewRepository.findAllByOrderByCreatedAtDesc());
    }
}
