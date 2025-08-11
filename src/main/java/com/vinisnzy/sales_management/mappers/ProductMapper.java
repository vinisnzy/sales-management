package com.vinisnzy.sales_management.mappers;

import com.vinisnzy.sales_management.dto.product.ProductRequestDTO;
import com.vinisnzy.sales_management.dto.product.ProductResponseDTO;
import com.vinisnzy.sales_management.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toProductResponseDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", ignore = true)
    Product toProduct(ProductRequestDTO productRequestDTO);

    void updateProduct(ProductRequestDTO data, @MappingTarget Product product);
}
