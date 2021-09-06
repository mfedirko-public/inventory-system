package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

/**
 * Basic inventory CRUD operations.
 */
public interface InventoryRepositoryService {
    /**
     * Returns a flux (paginated) of all inventory items
     *
     * @return 0-many inventory items
     */
    Flux<InventoryDTO> findAll();

    /**
     * Find inventory item by UUID
     *
     * @param uuid non-nullable
     * @return 0-1 inventory items
     */
    Mono<InventoryDTO> findByUuid(String uuid);

    /**
     * Create inventory item.
     * @param request non-nullable
     * @return the created inventory item
     */
    @Transactional
    Mono<InventoryDTO> createInventory(InventoryDTO request);

    /**
     * Delete inventory item by ID.
     * @param id non-nullable
     * @return the number of rows updated (0-many)
     */
    @Transactional
    Mono<Integer> deleteInventory(String id);

    /**
     * Update inventory item.
     * @param request non-nullable
     * @return the updated inventory item
     * @throws java.util.NoSuchElementException if inventory item is not found
     */
    @Transactional
    Mono<InventoryDTO> updateInventory(InventoryDTO request) throws NoSuchElementException;
}
