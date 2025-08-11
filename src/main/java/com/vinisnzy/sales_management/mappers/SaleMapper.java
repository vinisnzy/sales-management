package com.vinisnzy.sales_management.mappers;

import com.vinisnzy.sales_management.dto.sale.SaleResponseDTO;
import com.vinisnzy.sales_management.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SaleItemMapper.class)
public interface SaleMapper {

    @Mapping(target = "clientName", source = "client.name")
    SaleResponseDTO toSaleResponseDTO(Sale sale);
}
