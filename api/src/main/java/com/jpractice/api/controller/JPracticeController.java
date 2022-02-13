package com.jpractice.api.controller;

import com.jpractice.judge0api.feign.Judge0ApiClient;
import java.io.IOException;
import javax.annotation.PostConstruct;
import com.jpractice.judge0api.httpclient.CreateJavaSubmission;
import com.jpractice.judge0api.httpclient.CreateSubmissionToken;
import com.jpractice.judge0api.httpclient.GetJavaSubmission;
import com.jpractice.judge0api.httpclient.Judge0ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("jpractice")
public class JPracticeController {

    private final Judge0ApiService judge0ApiService;
    private final Judge0ApiClient judge0ApiClient;

    private CreateJavaSubmission createJavaSubmission;

    @PostConstruct
    public void init() {
        createJavaSubmission = CreateJavaSubmission.builder()
            .sourceCode("import java.time.LocalDateTime; "
                + "public class Main { "
                + "public static void main(String[] args) { "
                + "System.out.println(\"Current time: \" + LocalDateTime.now());"
                + "} "
                + "}")
            .build();
    }

    @GetMapping("stdout_http_response")
    public GetJavaSubmission getCompiledStdout() throws IOException, InterruptedException {
        CreateSubmissionToken token = judge0ApiService.post(createJavaSubmission);

        return judge0ApiService.get(token.getToken());
    }

    @GetMapping("stdout_feign_response")
    public GetJavaSubmission getJavaSubmission() {
        //All fields = "*"
        String fields = "stdout";
        boolean encoded = false;
        CreateSubmissionToken token =
            judge0ApiClient.createJavaSubmission(encoded, fields, createJavaSubmission);

        return judge0ApiClient.getJavaSubmission(token.getToken(), encoded, fields);
    }
}
