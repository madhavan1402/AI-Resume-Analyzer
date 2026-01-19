package com.maddy.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "https://ai-resume-analyzer-dusky-nine.vercel.app",
                                "https://ai-resume-analyzer-eix3ec8yv-madhavans-projects-a641427d.vercel.app",
                                "https://ai-resume-analyzer-dxb30h8fu-madhavans-projects-a641427d.vercel.app",
                                "https://ai-resume-analyzer-a6eiy6kw3-madhavans-projects-a641427d.vercel.app",
                                "https://ai-resume-analyzer-q9bngtb76-madhavans-projects-a641427d.vercel.app"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
