package com.vinisnzy.sales_management.dto.sale;

import java.time.LocalDateTime;

public record SaleResponseDTO(
        Long id,
        String clientName,
        SaleItemResponseDTO[] items,
        Double total,
        LocalDateTime createdAt
) {
}
