package com.jpractice.judge0api.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Artem Anosov
 */
@Configuration
@EnableFeignClients(basePackages = "com.jpractice.judge0api.feign")
public class ApiConfig {

}
