package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class R2DbcInventoryRepositoryService implements InventoryRepositoryService {
    private final InventoryRepository repository;

    public R2DbcInventoryRepositoryService(@Autowired(required = false) InventoryRepository repository) {
        System.out.println("did changes apply");
        this.repository = repository;
    }

    @Override
    public Flux<InventoryDTO> findAll() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public Mono<InventoryDTO> findById(String id) {
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
