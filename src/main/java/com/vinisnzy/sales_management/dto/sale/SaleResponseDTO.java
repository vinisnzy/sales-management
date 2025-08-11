package com.vinisnzy.sales_management.dto.sale;

import com.vinisnzy.sales_management.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        Long id,
        String clientName,
        List<SaleItemResponseDTO> items,
        BigDecimal total,
        SaleStatus status,
        LocalDateTime createdAt
) {
}
