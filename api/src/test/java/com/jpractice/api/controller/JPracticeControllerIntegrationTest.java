package com.jpractice.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpractice.api.JPracticeLauncher;
import com.jpractice.judge0api.submission.CreateJavaSubmission;
import java.io.IOException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Artem Anosov
 */
@Slf4j
@SpringBootTest(classes = JPracticeLauncher.class)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class JPracticeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void compileTestCode() {
        CreateJavaSubmission request = read(
            new ClassPathResource("json/request/jpractice-controller-compile-test-code-request.json"),
            CreateJavaSubmission.class);

        String response = mockMvc.perform(post("/jpractice/compile-code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        assertEquals(response, "Test");
    }

    private <T> T read(Resource resource, Class<T> tClass) {
        try {
            return mapper.readValue(resource.getInputStream(), tClass);
        } catch (IOException e) {
            throw new RuntimeException("Cannot perform json " + resource.getFilename() + " to " + tClass );
        }
    }
}
