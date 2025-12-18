package com.nhlstenden.groep3.digital_twin.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
public class AIController {

    @PostMapping("/uploadtoai")
    public ResponseEntity<?> uploadAndAnalyze(
            @RequestParam("image") MultipartFile file) throws Exception {

        String base64Image = Base64.getEncoder()
                .encodeToString(file.getBytes());

        String ollamaUrl = "http://ollama:11434/api/generate";

        String requestBody = """
        {
          "model": "llava",
          "prompt": "Describe what you see in this image",
          "images": ["%s"]
        }
        """.formatted(base64Image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.postForEntity(ollamaUrl, request, String.class);

        return ResponseEntity.ok(response.getBody());
    }
}
