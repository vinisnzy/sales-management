package com.vinisnzy.sales_management.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Positive
        Double price
) {
}
