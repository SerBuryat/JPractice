package com.jpractice.judge0api.httpclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Artem Anosov
 */
@Builder
@Data
public class CreateJavaSubmission {
    @JsonProperty("language_id")
    private final Integer languageId = 62;
    @JsonProperty("source_code")
    private String sourceCode;
}
