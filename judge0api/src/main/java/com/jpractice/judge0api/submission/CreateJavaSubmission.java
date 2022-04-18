package com.jpractice.judge0api.submission;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Artem Anosov
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateJavaSubmission {

    // 62 is java language code in Judge0Api
    @JsonProperty(value = "language_id", access = Access.READ_ONLY)
    private Integer languageId = 62;

    @JsonProperty("source_code")
    private String sourceCode;

    @Override
    public String toString() {
        return "CreateJavaSubmission{" +
            "languageId=" + languageId +
            ", sourceCode='" + sourceCode + '\'' +
            '}';
    }
}
