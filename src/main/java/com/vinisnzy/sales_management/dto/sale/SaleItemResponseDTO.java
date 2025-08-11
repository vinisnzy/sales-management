package com.vinisnzy.sales_management.dto.sale;

import java.math.BigDecimal;

public record SaleItemResponseDTO(
        Long id,
        String productName,
        Integer quantity,
        BigDecimal subtotal
) {
}
