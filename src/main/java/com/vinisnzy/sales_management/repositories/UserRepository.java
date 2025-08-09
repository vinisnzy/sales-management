package com.vinisnzy.sales_management.repositories;

import com.vinisnzy.sales_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
