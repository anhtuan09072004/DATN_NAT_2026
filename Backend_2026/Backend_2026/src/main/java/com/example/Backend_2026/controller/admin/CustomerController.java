package com.example.Backend_2026.controller.admin;
import com.example.Backend_2026.infrastructure.request.CreateCustomerRequest;
import com.example.Backend_2026.infrastructure.request.UpdateCustomerRequest;
import com.example.Backend_2026.infrastructure.response.CustomerResponse;
import com.example.Backend_2026.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin("*")

public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id,
                                   @Valid @RequestBody UpdateCustomerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
