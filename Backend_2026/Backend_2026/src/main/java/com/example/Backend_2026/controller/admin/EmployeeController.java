package com.example.Backend_2026.controller.admin;

import com.example.Backend_2026.infrastructure.request.CreateEmployeeRequest;
import com.example.Backend_2026.infrastructure.response.EmployeeResponse;
import com.example.Backend_2026.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody CreateEmployeeRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                                   @RequestBody CreateEmployeeRequest request) {
        return service.update(id, request);
    }
}
