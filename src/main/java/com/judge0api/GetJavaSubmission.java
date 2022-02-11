package com.judge0api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Artem Anosov
 */
@Data
@NoArgsConstructor
public class GetJavaSubmission {

    @JsonProperty("stdout")
    private String consoleOut;
}
