package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

public interface InventoryService {
    /**
     * Returns a flux (paginated) of all inventory items
     *
     * @return 0-many inventory items
     */
    Flux<InventoryDTO> findAll();

    /**
     * Find inventory item by ID
     *
     * @param id non-nullable
     * @return 0-1 inventory items
     */
    Mono<InventoryDTO> findById(String id);

    /**
     * Find inventory items by name using fuzzy/approximate search.
     *
     * @param name inventory item name
     * @return 0-1 inventory items
     */
    Flux<InventoryDTO> findByNameFuzzy(String name);

    /**
     * Create inventory item.
     * @param request non-nullable
     * @return the created inventory item
     */
    Mono<InventoryDTO> createInventory(InventoryDTO request);

    /**
     * Delete inventory item by ID.
     * @param id non-nullable
     * @return the number of rows updated (0-many)
     */
    Mono<Integer> deleteInventory(String id);

    /**
     * Update inventory item.
     * @param request non-nullable
     * @return the updated inventory item
     * @throws java.util.NoSuchElementException if inventory item is not found
     */
    Mono<InventoryDTO> updateInventory(InventoryDTO request) throws NoSuchElementException;
}
