package com.sthi.re.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sthi.model.Users;
import com.sthi.repo.UsersRepository;

@Service
public class UserProfileService {

    private final UsersRepository usersRepository;
    private final R2StorageService r2StorageService;

    public UserProfileService(
            UsersRepository usersRepository,
            R2StorageService r2StorageService
    ) {
        this.usersRepository = usersRepository;
        this.r2StorageService = r2StorageService;
    }

    public String uploadProfilePicture(Integer userId, MultipartFile file) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Upload to R2
        String objectKey =
                r2StorageService.uploadUserProfileImage(file, userId);

        // Convert to public URL
        String imageUrl =
                r2StorageService.buildPublicUrl(objectKey);

        // Save URL in DB
        user.setProfilePicUrl(imageUrl);
        usersRepository.save(user);

        return imageUrl;
    }

}