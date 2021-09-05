package com.example.inventory.controller;

import com.example.inventory.model.InventoryDTO;
import com.example.inventory.model.Response;
import com.example.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    @GetMapping
    public Flux<InventoryDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<InventoryDTO> findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @GetMapping(params = {"name"})
    public Flux<InventoryDTO> findByName(@RequestParam("name") String name) {
        return service.findByNameFuzzy(name);
    }

    @PostMapping
    public Mono<InventoryDTO> createInventory(@RequestBody InventoryDTO request) {
        return service.createInventory(request);
    }

    @DeleteMapping("/{id}")
    public Mono<Response> deleteInventory(@PathVariable("id") String id) {
        return service.deleteInventory(id)
                .map(rowsUpd -> {
                    if (rowsUpd == 0) return Response.noData(null);
                    else return Response.success();
                });
    }

    @PutMapping
    public Mono<InventoryDTO> updateInventory(@RequestBody InventoryDTO request) {
        return service.updateInventory(request);
    }
}
