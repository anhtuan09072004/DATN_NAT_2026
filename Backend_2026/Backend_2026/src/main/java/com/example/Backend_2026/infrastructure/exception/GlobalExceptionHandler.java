package com.example.Backend_2026.infrastructure.exception;

import com.example.Backend_2026.infrastructure.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiErrorResponse> handleRuntime(RuntimeException e) {
//
//        ApiErrorResponse response = new ApiErrorResponse();
//        response.setSuccess(false);
//        response.setMessage(e.getMessage());
//
//        return ResponseEntity
//                .status(400)
////                .contentType(org.springframework.http.MediaType.APPLICATION_JSON) // 🔥 QUAN TRỌNG
//                .body(response);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
//
//        ApiErrorResponse response = new ApiErrorResponse();
//        response.setSuccess(false);
//        response.setMessage("Lỗi hệ thống");
//
//        return ResponseEntity
//                .status(500)
////                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
//                .body(response);
//    }
@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
    );

    return errors;
}
}