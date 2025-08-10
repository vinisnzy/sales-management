package com.vinisnzy.sales_management.model.clients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("NATURAL_PERSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPerson extends Client {
    @Column(nullable = false, unique = true)
    private String cpf;

    @Override
    public String getDocument() {
        return getCpf();
    }
}
