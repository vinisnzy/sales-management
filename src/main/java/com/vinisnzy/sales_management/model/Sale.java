package com.vinisnzy.sales_management.model;

import com.vinisnzy.sales_management.enums.SaleStatus;
import com.vinisnzy.sales_management.model.clients.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SaleItem> items = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime completedAt;

    @Column
    private LocalDateTime canceledAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SaleStatus status = SaleStatus.PENDING;

    public Sale(Client client) {
        this.client = client;
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(SaleItem item) {
        items.add(item);
        item.setSale(this);
        total = total.add(item.getSubtotal());
    }

    public void removeItem(Long itemId) {
        SaleItem itemToRemove = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + itemId));

        items.remove(itemToRemove);
        total = total.subtract(itemToRemove.getSubtotal());
    }
}
