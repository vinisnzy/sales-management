package com.vinisnzy.sales_management.service;

import com.vinisnzy.sales_management.dto.sale.SaleRemoveItemDTO;
import com.vinisnzy.sales_management.dto.sale.SaleItemRequestDTO;
import com.vinisnzy.sales_management.dto.sale.SaleResponseDTO;
import com.vinisnzy.sales_management.enums.SaleStatus;
import com.vinisnzy.sales_management.mappers.SaleMapper;
import com.vinisnzy.sales_management.model.Sale;
import com.vinisnzy.sales_management.model.SaleItem;
import com.vinisnzy.sales_management.repositories.SaleItemRepository;
import com.vinisnzy.sales_management.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ClientService clientService;
    private final ProductService productService;
    private final SaleMapper mapper;

    public SaleResponseDTO createSale(Long clientId) {
        var client = clientService.findById(clientId);
        var sale = new Sale(client);
        return mapper.toSaleResponseDTO(saleRepository.save(sale));
    }

    public SaleResponseDTO getSaleById(Long id) {
        var sale = findById(id);
        return mapper.toSaleResponseDTO(sale);
    }

    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(mapper::toSaleResponseDTO)
                .toList();
    }

    public SaleResponseDTO addItemToSale(SaleItemRequestDTO data) {
        var sale = findById(data.saleId());
        var product = productService.findById(data.productId());
        var item = new SaleItem(product, data.quantity(), sale);
        sale.addItem(item);
        saleItemRepository.save(item);
        return mapper.toSaleResponseDTO(saleRepository.save(sale));
    }

    public SaleResponseDTO removeItemFromSale(SaleRemoveItemDTO data) {
        var sale = findById(data.saleId());
        sale.removeItem(data.itemId());
        return mapper.toSaleResponseDTO(saleRepository.save(sale));
    }

    public void cancelSale(Long saleId) {
        var sale = findById(saleId);
        sale.setStatus(SaleStatus.CANCELED);
        sale.setCanceledAt(LocalDateTime.now());
        saleRepository.save(sale);
    }

    public void completeSale(Long saleId) {
        var sale = findById(saleId);
        sale.setStatus(SaleStatus.COMPLETED);
        sale.setCompletedAt(LocalDateTime.now());
        saleRepository.save(sale);
    }

    private Sale findById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
    }
}
