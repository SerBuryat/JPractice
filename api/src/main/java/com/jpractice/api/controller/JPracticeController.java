package com.jpractice.api.controller;

import com.jpractice.judge0api.feign.Judge0ApiClient;
import com.jpractice.judge0api.submission.CreateJavaSubmission;
import com.jpractice.judge0api.submission.CreateSubmissionToken;
import com.jpractice.judge0api.submission.GetJavaSubmission;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("jpractice")
public class JPracticeController {

    private final Judge0ApiClient judge0ApiClient;

    private CreateJavaSubmission createJavaSubmission;

    @PostConstruct
    public void init() {
        createJavaSubmission = CreateJavaSubmission.builder()
            .sourceCode("import java.time.LocalDateTime; "
                + "public class Main { "
                + "public static void main(String[] args) { "
                + "System.out.println(\"Current time: \" + LocalDateTime.now());"
                + " } "
                + "}")
            .build();
    }

    @GetMapping("compile-java-test")
    public GetJavaSubmission getJavaSubmission() {
        //All fields = "*"
        String fields = "stdout";
        boolean encoded = false;
        CreateSubmissionToken token =
            judge0ApiClient.createJavaSubmission(encoded, fields, createJavaSubmission);

        return judge0ApiClient.getJavaSubmission(token.getToken(), encoded, fields);
    }
}
