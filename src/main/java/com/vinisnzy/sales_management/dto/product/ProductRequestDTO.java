package com.vinisnzy.sales_management.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Positive
        BigDecimal price
) {
}
