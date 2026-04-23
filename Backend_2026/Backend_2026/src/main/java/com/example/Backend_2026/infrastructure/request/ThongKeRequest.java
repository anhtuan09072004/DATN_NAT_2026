package com.example.Backend_2026.infrastructure.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ThongKeRequest {
    private LocalDate fromDate;
    private LocalDate toDate;
}
