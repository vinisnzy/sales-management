package com.vinisnzy.sales_management.model.clients;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("LEGAL_ENTITY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntity extends Client {
    @Column(nullable = false, unique = true)
    private String cnpj;
}
