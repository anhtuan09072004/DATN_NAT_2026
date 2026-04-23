package com.example.Backend_2026.infrastructure.request;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UpdateCustomerRequest {

    @NotBlank(message = "Tên không được để trống")
    private String ten;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Pattern(regexp = "^(0[0-9]{9})$", message = "SĐT không hợp lệ")
    private String soDienThoai;

    private String gioiTinh;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate ngaySinh;
}