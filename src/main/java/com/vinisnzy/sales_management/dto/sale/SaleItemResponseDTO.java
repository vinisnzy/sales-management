package com.vinisnzy.sales_management.dto.sale;

public record SaleItemResponseDTO(
        Long id,
        String productName,
        Integer quantity,
        Double subtotal
) {
}
