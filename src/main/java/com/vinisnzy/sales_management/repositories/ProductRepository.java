package com.vinisnzy.sales_management.repositories;

import com.vinisnzy.sales_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}