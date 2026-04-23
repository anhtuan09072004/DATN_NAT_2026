package com.example.Backend_2026.controller.client;

import com.example.Backend_2026.infrastructure.request.ProfileRequest;
import com.example.Backend_2026.infrastructure.response.ProfileResponse;
import com.example.Backend_2026.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/me")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponse getMyProfile(@RequestParam Long userId) {
        return profileService.getMyProfile(userId);
    }

    @PutMapping
    public ProfileResponse updateMyProfile(
            @RequestParam Long userId,
            @RequestBody ProfileRequest request) {
        return profileService.updateMyProfile(userId, request);
    }
}