package com.jpractice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jpractice")
public class JPracticeLauncher {

    public static void main(String[] args) {
        SpringApplication.run(JPracticeLauncher.class, args);
    }
}
