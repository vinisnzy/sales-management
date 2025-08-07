package com.vinisnzy.sales_management.dto.auth;

public record LoginRequestDTO(
        String email,
        String password
) {
}
