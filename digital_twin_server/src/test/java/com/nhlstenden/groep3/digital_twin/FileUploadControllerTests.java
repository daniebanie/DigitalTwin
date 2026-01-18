package com.nhlstenden.groep3.digital_twin;

import com.nhlstenden.groep3.digital_twin.Controller.FileUploadController;
import com.nhlstenden.groep3.digital_twin.Entity.AiReview;
import com.nhlstenden.groep3.digital_twin.Repository.AiReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FileUploadController.class)
class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AiReviewRepository aiReviewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test 1:
     * Uploading an EMPTY file should return 400 Bad Request
     */
    @Test
    void uploadFile_emptyFile_returnsBadRequest() throws Exception {
        MockMultipartFile emptyFile =
                new MockMultipartFile("image", "", "image/png", new byte[0]);

        mockMvc.perform(multipart("/upload")
                        .file(emptyFile))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No file received"));
    }

    /**
     * Test 2:
     * GET /ai-reviews should return reviews from the repository
     */
    @Test
    void getAiReviews_returnsReviewsFromDatabase() throws Exception {
        AiReview review1 = new AiReview();
        review1.setReviewContent("Test review 1");
        review1.setImagePath("/uploads/test1.png");

        AiReview review2 = new AiReview();
        review2.setReviewContent("Test review 2");
        review2.setImagePath("/uploads/test2.png");

        when(aiReviewRepository.findAllByOrderByCreatedAtDesc())
                .thenReturn(List.of(review1, review2));

        mockMvc.perform(get("/ai-reviews")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reviewContent").value("Test review 1"))
                .andExpect(jsonPath("$[0].imagePath").value("/uploads/test1.png"))
                .andExpect(jsonPath("$[1].reviewContent").value("Test review 2"))
                .andExpect(jsonPath("$[1].imagePath").value("/uploads/test2.png"));
    }
}
