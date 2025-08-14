package com.vinisnzy.sales_management.controller;

import com.vinisnzy.sales_management.dto.auth.LoginRequestDTO;
import com.vinisnzy.sales_management.dto.auth.LoginResponseDTO;
import com.vinisnzy.sales_management.dto.auth.RegisterRequestDTO;
import com.vinisnzy.sales_management.dto.auth.RegisterResponseDTO;
import com.vinisnzy.sales_management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO data) {
        return ResponseEntity.ok(service.register(data));
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO data) {
        return ResponseEntity.ok(service.login(data));
    }
}
