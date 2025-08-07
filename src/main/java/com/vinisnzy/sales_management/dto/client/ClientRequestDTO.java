package com.vinisnzy.sales_management.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Name cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Address cannot be blank")
        String address,

        @NotBlank(message = "Telephone cannot be blank")
        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "Telephone must be between 10 and 15 digits, optionally starting with '+'"
        )
        String telephone,

        @NotBlank(message = "Type cannot be blank")
        @Pattern(
                regexp = "^(NATURAL_PERSON|LEGAL_ENTITY)$",
                message = "Type must be either 'NATURAL_PERSON' or 'LEGAL_ENTITY'"
        )
        String type,

        @NotBlank(message = "Document cannot be blank")
        @Pattern(
                regexp = "^(\\d{11}|\\d{14})$",
                message = "Document must be either 11 digits (CPF) or 14 digits (CNPJ)"
        )
        String document
) {
}
