package com.sthi.re.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class R2StorageService {

    private final S3Client s3Client;

    @Value("${cloudflare.r2.bucket}")
    private String bucketName;

    @Value("${cloudflare.r2.public-base-url}")
    private String publicBaseUrl;

    public R2StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    // =====================================================
    // GENERIC UPLOADER
    // =====================================================
    private String uploadFile(MultipartFile file, String folderPath) {

        try {

            String fileName =
                    UUID.randomUUID() + "-" + file.getOriginalFilename();

            String objectKey = folderPath + "/" + fileName;

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(
                            file.getInputStream(),
                            file.getSize()
                    )
            );

            return objectKey;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload to R2", e);
        }
    }

    // =====================================================
    // PROJECT STORAGE
    // =====================================================

    public String uploadProjectImage(
            MultipartFile file,
            Long projectId
    ) {
        validateImage(file);
        return uploadFile(file,
                "projects/" + projectId + "/images");
    }

    public String uploadProjectDocument(
            MultipartFile file,
            Long projectId,
            String documentType
    ) {
        validateDocument(file);

        String safeType = documentType == null
                ? "other"
                : documentType.toLowerCase().replaceAll("\\s+", "_");

        return uploadFile(file,
                "projects/" + projectId + "/documents/" + safeType);
    }

    // =====================================================
    // PROPERTY STORAGE
    // =====================================================

    public String uploadPropertyImage(
            MultipartFile file,
            Long propertyId
    ) {
        validateImage(file);
        return uploadFile(file,
                "properties/" + propertyId + "/images");
    }

    public String uploadPropertyDocument(
            MultipartFile file,
            Long propertyId,
            String documentType
    ) {
        validateDocument(file);

        String safeType = documentType == null
                ? "other"
                : documentType.toLowerCase().replaceAll("\\s+", "_");

        return uploadFile(file,
                "properties/" + propertyId + "/documents/" + safeType);
    }

    // =====================================================
    // DELETE FILE (ONLY WHEN EXPLICITLY CALLED)
    // =====================================================

    public void deleteFile(String objectKey) {

        if (objectKey == null || objectKey.isBlank())
            return;

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        s3Client.deleteObject(request);
    }

    // =====================================================
    // PUBLIC URL BUILDER
    // =====================================================

    public String buildPublicUrl(String objectKey) {

        if (objectKey == null || objectKey.isBlank())
            return null;

        if (objectKey.startsWith("http"))
            return objectKey;

        return publicBaseUrl + "/" + objectKey;
    }

    // =====================================================
    // VALIDATIONS
    // =====================================================

    private void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("Image file required");

        if (file.getSize() > 5 * 1024 * 1024)
            throw new IllegalArgumentException("Max image size 5MB");

        String type = file.getContentType();

        if (type == null ||
                !(type.equals("image/jpeg")
                        || type.equals("image/png")
                        || type.equals("image/webp")))
            throw new IllegalArgumentException("Only JPG, PNG, WEBP allowed");
    }

    private void validateDocument(MultipartFile file) {

        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("Document file required");

        if (file.getSize() > 20 * 1024 * 1024)
            throw new IllegalArgumentException("Max document size 20MB");

        String type = file.getContentType();

        if (type == null ||
                !(type.equals("application/pdf")
                        || type.equals("image/png")
                        || type.equals("image/jpeg")))
            throw new IllegalArgumentException("Only PDF/JPG/PNG allowed");
    }
    
    //Userprofile Save
 // =====================================================
 // USER PROFILE STORAGE
 // =====================================================

 public String uploadUserProfileImage(
         MultipartFile file,
         Integer userId
 ) {
     validateImage(file);

     return uploadFile(
             file,
             "users/" + userId + "/profile"
     );
 }
}
