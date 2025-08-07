package com.vinisnzy.sales_management.dto.client;

public record ClientResponseDTO(
        Long id,
        String name,
        String email,
        String address,
        String telephone,
        String type, // "NATURAL_PERSON" or "LEGAL_ENTITY"
        String document // CPF or CNPJ
) {
}
