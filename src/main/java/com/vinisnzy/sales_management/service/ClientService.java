package com.vinisnzy.sales_management.service;

import com.vinisnzy.sales_management.dto.client.ClientRequestDTO;
import com.vinisnzy.sales_management.dto.client.ClientResponseDTO;
import com.vinisnzy.sales_management.enums.ClientType;
import com.vinisnzy.sales_management.mappers.ClientMapper;
import com.vinisnzy.sales_management.model.clients.Client;
import com.vinisnzy.sales_management.model.clients.LegalEntity;
import com.vinisnzy.sales_management.model.clients.NaturalPerson;
import com.vinisnzy.sales_management.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper mapper;
    private final ClientRepository repository;

    public ClientResponseDTO createClient(ClientRequestDTO data) {
        if (ClientType.LEGAL_ENTITY.equals(data.type())) {
            var client = mapper.toLegalEntity(data);
            return mapper.toClientResponseDTO(repository.save(client));
        } else if (ClientType.NATURAL_PERSON.equals(data.type())) {
            var client = mapper.toNaturalPerson(data);
            return mapper.toClientResponseDTO(repository.save(client));
        }
        throw new IllegalArgumentException("Invalid client type: " + data.type());
    }

    public List<ClientResponseDTO> getAllClients() {
        return repository.findAll().stream()
                .map(mapper::toClientResponseDTO)
                .toList();
    }

    public ClientResponseDTO getClientById(Long id) {
        return mapper.toClientResponseDTO(findById(id));
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO data) {
        var client = findById(id);

        if (ClientType.LEGAL_ENTITY.equals(data.type())) {
            mapper.updateLegalEntity(data, (LegalEntity) client);
            return mapper.toClientResponseDTO(repository.save(client));
        } else if (ClientType.NATURAL_PERSON.equals(data.type())) {
            mapper.updateNaturalPerson(data, (NaturalPerson) client);
            return mapper.toClientResponseDTO(repository.save(client));
        }
        throw new IllegalArgumentException("Invalid client type: " + data.type());
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }

    protected Client findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
    }
}
