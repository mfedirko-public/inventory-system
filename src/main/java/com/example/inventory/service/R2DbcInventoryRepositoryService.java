package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class R2DbcInventoryRepositoryService implements InventoryRepositoryService {
    private final InventoryRepository repository;

    @Override
    public Flux<InventoryDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<InventoryDTO> findByUuid(String uuid) {
        return repository.findByUuid(uuid);
    }


    @Override
    public Mono<InventoryDTO> createInventory(InventoryDTO request) {
        return repository.save(request);
    }

    @Override
    public Mono<Integer> deleteInventory(String id) {
        return repository.deleteByUuid(id);
    }

    @Override
    public Mono<InventoryDTO> updateInventory(InventoryDTO request) throws NoSuchElementException {
        return repository.existsById(request.getId())
                .handle((exists, sink) -> {
                    if (exists) {
                        repository.save(request)
                                .subscribe(sink::next,
                                        sink::error,
                                        sink::complete);
                    } else {
                        sink.error(new NoSuchElementException("No inventory exists with id " + request.getId()));
                    }
                });
    }
}
