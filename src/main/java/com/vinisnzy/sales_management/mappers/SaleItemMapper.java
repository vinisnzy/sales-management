package com.vinisnzy.sales_management.mappers;

import com.vinisnzy.sales_management.dto.sale.SaleItemResponseDTO;
import com.vinisnzy.sales_management.model.SaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {

    @Mapping(target = "productName", source = "product.name")
    SaleItemResponseDTO toSaleItemResponseDTO(SaleItem saleItem);
}
