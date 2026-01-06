package com.nhlstenden.groep3.digital_twin.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    private static final String UPLOAD_DIR = "src/main/resources/uploads";
    private static final String OLLAMA_URL = "http://ollama:11434/api/generate";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file received");
            }

            // 1️⃣ Save file to disk
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // 2️⃣ Convert image → Base64
            String base64Image = Base64.getEncoder()
                    .encodeToString(file.getBytes());

            // 3️⃣ Build Ollama request
            String requestBody = """
            {
              "model": "llava",
              "prompt": "Describe what you see in this image in 20 words or less",
              "images": ["%s"],
              "stream": false
            }
            """.formatted(base64Image);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request =
                    new HttpEntity<>(requestBody, headers);

            // 4️⃣ Call Ollama
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> aiResponse =
                    restTemplate.postForEntity(OLLAMA_URL, request, Map.class);

            // 5️⃣ Return combined response
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
