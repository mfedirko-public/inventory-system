package com.example.inventory.controller;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.model.Response;
import com.example.inventory.service.InventorySearchService;
import com.example.inventory.service.InventoryRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryRepositoryService repoService;
    private final InventorySearchService searchService;

    @GetMapping
    public Flux<InventoryDTO> findAll() {
        return repoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<InventoryDTO> findById(@PathVariable("id") String id) {
        return repoService.findById(id);
    }

    @GetMapping(params = {"name"})
    public Flux<InventoryDTO> findByName(@RequestParam("name") String name) {
        return searchService.findByNameFuzzy(name);
    }

    @PostMapping
    public Mono<InventoryDTO> createInventory(@RequestBody InventoryDTO request) {
        return repoService.createInventory(request);
    }

    @DeleteMapping("/{id}")
    public Mono<Response> deleteInventory(@PathVariable("id") String id) {
        return repoService.deleteInventory(id)
                .map(rowsUpd -> {
                    if (rowsUpd == 0) return Response.noData(null);
                    else return Response.success();
                });
    }

    @PutMapping
    public Mono<InventoryDTO> updateInventory(@RequestBody InventoryDTO request) {
        return repoService.updateInventory(request);
    }
}
