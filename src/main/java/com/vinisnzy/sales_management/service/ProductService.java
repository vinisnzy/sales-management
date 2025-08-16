package com.vinisnzy.sales_management.service;

import com.vinisnzy.sales_management.dto.product.ProductRequestDTO;
import com.vinisnzy.sales_management.dto.product.ProductResponseDTO;
import com.vinisnzy.sales_management.exceptions.EntityNotFoundException;
import com.vinisnzy.sales_management.mappers.ProductMapper;
import com.vinisnzy.sales_management.model.Product;
import com.vinisnzy.sales_management.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductResponseDTO createProduct(ProductRequestDTO data) {
        var product = mapper.toProduct(data);
        return mapper.toProductResponseDTO(repository.save(product));
    }

    public List<ProductResponseDTO> getAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toProductResponseDTO)
                .toList();
    }

    public ProductResponseDTO getProductById(Long id) {
        return mapper.toProductResponseDTO(findById(id));
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO data) {
        var product = findById(id);
        mapper.updateProduct(data, product);
        return mapper.toProductResponseDTO(repository.save(product));
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }
}
