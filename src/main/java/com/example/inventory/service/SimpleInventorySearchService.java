package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SimpleInventorySearchService implements InventorySearchService {
    private final InventoryRepository repository;

    @Override
    public Flux<InventoryDTO> findByNameFuzzy(String name) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
