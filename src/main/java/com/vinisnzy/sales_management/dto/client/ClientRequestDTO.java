package com.vinisnzy.sales_management.dto.client;

import com.vinisnzy.sales_management.enums.ClientType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
                regexp = "^\\+?55?([1-9]{2})?(\\d{4,5}\\d{4})$",
                message = "Telephone must be between 10 and 15 digits, optionally starting with country code '55'"
        )
        String telephone,

        @NotNull(message = "Type cannot be null")
        ClientType type,

        @NotBlank(message = "Document cannot be blank")
        @Pattern(
                regexp = "^(\\d{11}|\\d{14})$",
                message = "Document must be either 11 digits (CPF) or 14 digits (CNPJ)"
        )
        String document
) {
}
