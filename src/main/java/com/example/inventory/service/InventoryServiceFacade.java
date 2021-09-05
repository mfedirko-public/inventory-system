package com.example.inventory.service;

import com.example.inventory.integration.ElasticSearchClient;
import com.example.inventory.model.InventoryDTO;
import com.example.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InventoryServiceFacade implements InventoryService {
    private final ElasticSearchClient esClient;
    private final InventoryRepository repository;

    @Override
    public Flux<InventoryDTO> findAll() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Mono<InventoryDTO> findById(String id) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Flux<InventoryDTO> findByNameFuzzy(String name) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Mono<InventoryDTO> createInventory(InventoryDTO request) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Mono<Integer> deleteInventory(String id) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Mono<InventoryDTO> updateInventory(InventoryDTO request) throws NoSuchElementException {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
