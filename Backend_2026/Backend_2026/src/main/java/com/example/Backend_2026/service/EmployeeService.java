package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.CreateEmployeeRequest;
import com.example.Backend_2026.infrastructure.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(CreateEmployeeRequest request);

    List<EmployeeResponse> getAll();

    void delete(Long id);

    EmployeeResponse update(Long id, CreateEmployeeRequest request);
}
