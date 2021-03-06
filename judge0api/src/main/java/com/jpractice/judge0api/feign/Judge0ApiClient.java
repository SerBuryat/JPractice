package com.jpractice.judge0api.feign;

import com.jpractice.judge0api.config.ClientConfig;
import com.jpractice.judge0api.submission.CreateJavaSubmission;
import com.jpractice.judge0api.submission.CreateSubmissionToken;
import com.jpractice.judge0api.submission.GetJavaSubmission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Artem Anosov
 */
@FeignClient(
    value = "${feign.clients.judge0api.name}",
    url = "${feign.clients.judge0api.url}",
    configuration = ClientConfig.class)
public interface Judge0ApiClient {

    @GetMapping("submissions/{token}")
    GetJavaSubmission getJavaSubmission(
        @PathVariable String token,
        @RequestParam(name = "base64_encoded") boolean encoded,
        @RequestParam String fields
    );

    @PostMapping("submissions")
    CreateSubmissionToken createJavaSubmission (
        @RequestParam(name = "base64_encoded") boolean encoded,
        @RequestParam String fields,
        @RequestBody CreateJavaSubmission createJavaSubmission
    );
}
