package com.example.inventory.service;

import com.example.inventory.model.InventoryDTO;
import reactor.core.publisher.Flux;

/**
 * More complex inventory search operations, such as fuzzy searches, tokenization based on English language, etc.
 */
public interface InventorySearchService {
    /**
     * Find inventory items by name using fuzzy/approximate search.
     *
     * @param name inventory item name
     * @return 0-1 inventory items
     */
    Flux<InventoryDTO> findByNameFuzzy(String name);
}
