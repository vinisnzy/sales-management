package com.vinisnzy.sales_management.dto.sale;

import jakarta.validation.constraints.Positive;

public record SaleItemRequestDTO(
    Long saleId,
    Long productId,

    @Positive
    Integer quantity
) {
}
