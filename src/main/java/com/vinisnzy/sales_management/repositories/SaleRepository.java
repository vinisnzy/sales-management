package com.vinisnzy.sales_management.repositories;

import com.vinisnzy.sales_management.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
