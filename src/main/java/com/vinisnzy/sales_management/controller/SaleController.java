package com.vinisnzy.sales_management.controller;

import com.vinisnzy.sales_management.dto.sale.SaleRemoveItemDTO;
import com.vinisnzy.sales_management.dto.sale.SaleItemRequestDTO;
import com.vinisnzy.sales_management.dto.sale.SaleResponseDTO;
import com.vinisnzy.sales_management.service.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping("/client/{clientId}")
    public ResponseEntity<SaleResponseDTO> createSale(@PathVariable Long clientId) {
        var sale = service.createSale(clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        var sales = service.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        var sale = service.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/add-item")
    public ResponseEntity<SaleResponseDTO> addItemToSale(@Valid @RequestBody SaleItemRequestDTO data) {
        var sale = service.addItemToSale(data);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/remove-item")
    public ResponseEntity<SaleResponseDTO> removeItemFromSale(@Valid @RequestBody SaleRemoveItemDTO data) {
        var sale = service.removeItemFromSale(data);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/cancel/{saleId}")
    public ResponseEntity<Void> cancelSale(@PathVariable Long saleId) {
        service.cancelSale(saleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("complete/{saleId}")
    public ResponseEntity<Void> completeSale(@PathVariable Long saleId) {
        service.completeSale(saleId);
        return ResponseEntity.noContent().build();
    }
}
