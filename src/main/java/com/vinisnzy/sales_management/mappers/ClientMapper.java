package com.vinisnzy.sales_management.mappers;

import com.vinisnzy.sales_management.dto.client.ClientRequestDTO;
import com.vinisnzy.sales_management.dto.client.ClientResponseDTO;
import com.vinisnzy.sales_management.enums.ClientType;
import com.vinisnzy.sales_management.model.clients.Client;
import com.vinisnzy.sales_management.model.clients.LegalEntity;
import com.vinisnzy.sales_management.model.clients.NaturalPerson;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "type", expression = "java(identifyClientType(client))")
    @Mapping(target = "document", expression = "java(client.getDocument())")
    ClientResponseDTO toClientResponseDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(source = "document", target = "cnpj")
    LegalEntity toLegalEntity(ClientRequestDTO clientRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(source = "document", target = "cpf")
    NaturalPerson toNaturalPerson(ClientRequestDTO clientRequestDTO);

    @Name("identifyClientType")
    default String identifyClientType(Client client) {
        if (client instanceof NaturalPerson) {
            return ClientType.NATURAL_PERSON.name();
        } else if (client instanceof LegalEntity) {
            return ClientType.LEGAL_ENTITY.name();
        }
        return null;
    }
}
