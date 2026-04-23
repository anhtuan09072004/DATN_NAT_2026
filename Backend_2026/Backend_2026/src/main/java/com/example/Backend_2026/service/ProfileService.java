package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.ProfileRequest;
import com.example.Backend_2026.infrastructure.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getMyProfile(Long userId);
    ProfileResponse updateMyProfile(Long userId, ProfileRequest request);
}
