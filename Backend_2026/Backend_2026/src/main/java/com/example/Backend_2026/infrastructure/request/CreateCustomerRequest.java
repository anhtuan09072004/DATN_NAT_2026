package com.example.Backend_2026.infrastructure.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CreateCustomerRequest {
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 255, message = "Tên tối đa 255 ký tự")
    private String ten;

    @NotBlank(message = "Username không được để trống")
    @Size(min = 4, max = 50, message = "Username từ 4 - 50 ký tự")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, message = "Password tối thiểu 6 ký tự")
    private String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[0-9]{9})$", message = "SĐT không hợp lệ")
    private String soDienThoai;

    private String gioiTinh;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate ngaySinh;
}
