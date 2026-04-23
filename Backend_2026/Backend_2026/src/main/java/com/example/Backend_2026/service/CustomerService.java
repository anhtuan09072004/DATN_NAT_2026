package com.example.Backend_2026.service;

import com.example.Backend_2026.infrastructure.request.CreateCustomerRequest;
import com.example.Backend_2026.infrastructure.request.UpdateCustomerRequest;
import com.example.Backend_2026.infrastructure.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CreateCustomerRequest request);

    List<CustomerResponse> getAll();

    CustomerResponse update(Long id, UpdateCustomerRequest request);

    void delete(Long id);
}
