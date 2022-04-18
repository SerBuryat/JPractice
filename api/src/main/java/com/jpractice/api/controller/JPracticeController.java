package com.jpractice.api.controller;

import com.jpractice.judge0api.feign.Judge0ApiClient;
import com.jpractice.judge0api.submission.CreateJavaSubmission;
import com.jpractice.judge0api.submission.CreateSubmissionToken;
import com.jpractice.judge0api.submission.GetJavaSubmission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("jpractice")
public class JPracticeController {

    private final Judge0ApiClient judge0ApiClient;

    @PostMapping("compile-code")
    public String getJavaSubmission(@RequestBody CreateJavaSubmission createJavaSubmission) {
        //Stdout - return only console output
        String fields = "stdout";
        boolean encoded = false;
        CreateSubmissionToken token =
            judge0ApiClient.createJavaSubmission(encoded, fields, createJavaSubmission);

        GetJavaSubmission getJavaSubmission =
            judge0ApiClient.getJavaSubmission(token.getToken(), encoded, fields);

        // JudgeApi0 returns string with empty line;
        return getJavaSubmission.getConsoleOut().trim();
    }
}
