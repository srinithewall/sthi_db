package com.sthi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @Value("${cors.allowed-origins:*}")
    private String[] allowedOrigins;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Convert Windows path to proper file URL format
        String filePath = "file:" + uploadDir.replace("\\", "/") + "/";
        
        registry.addResourceHandler("/uploads/products/**")
                .addResourceLocations(filePath)
                .setCachePeriod(3600);
        
        System.out.println("Static resource handler configured for: " + filePath);
    }
    

}