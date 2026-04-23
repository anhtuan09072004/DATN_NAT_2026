package com.example.Backend_2026.infrastructure.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MauSacRequest {

    @NotBlank(message = "Tên màu sắc không được để trống")
    @Size(min = 2, max = 50, message = "Tên màu sắc phải từ 2 đến 50 ký tự")
    private String ten;
}