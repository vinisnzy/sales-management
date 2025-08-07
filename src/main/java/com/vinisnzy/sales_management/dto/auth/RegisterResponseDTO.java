package com.vinisnzy.sales_management.dto.auth;

public record RegisterResponseDTO(
        String name,
        String email,
        String password
) {
}
