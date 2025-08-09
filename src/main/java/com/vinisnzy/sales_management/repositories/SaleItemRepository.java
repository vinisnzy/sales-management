package com.vinisnzy.sales_management.repositories;

import com.vinisnzy.sales_management.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
