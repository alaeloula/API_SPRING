package com.example.quiz;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.example.gestionemployee")
@Data
public class CustomProperties {
    private String apiUrl;
}
