package com.vinisnzy.sales_management.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
