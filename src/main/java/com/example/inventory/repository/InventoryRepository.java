package com.example.inventory.repository;

import com.example.inventory.model.InventoryDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface InventoryRepository extends ReactiveCrudRepository<InventoryDTO, Integer> {

    Mono<InventoryDTO> findByUuid(String uuid);
    Mono<Integer> deleteByUuid(String uuid);
    Flux<InventoryDTO> findByNameLike(String name);

}
