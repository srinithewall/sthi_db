package com.sthi.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {
    
    private final Path fileStorageLocation;
    
    // Remove baseUrl dependency from this service
    // @Value("${app.base-url}")
    // private String baseUrl;
    
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        // This creates uploads folder in project root
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("Upload directory: " + this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory: " + uploadDir, ex);
        }
    }
    
    public String storeFile(MultipartFile file, Long productId) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            
            // Validate image type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Only image files are allowed");
            }
            
            // Generate safe filename
            String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileExtension = getFileExtension(originalFileName);
            String fileName = "product_" + productId + "_" + System.currentTimeMillis() + fileExtension;
            
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            // CHANGE: Return ONLY filename instead of full URL
            return fileName;
            
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file: " + ex.getMessage(), ex);
        }
    }
    
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        return (lastDotIndex > 0) ? fileName.substring(lastDotIndex) : ".jpg";
    }
    
    public void deleteFile(String fileName) { // CHANGE: parameter is now filename, not URL
        try {
            if (fileName != null) {
                // CHANGE: Directly use filename, no need to extract from URL
                Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
                Files.deleteIfExists(filePath);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Could not delete file: " + ex.getMessage(), ex);
        }
    }
}