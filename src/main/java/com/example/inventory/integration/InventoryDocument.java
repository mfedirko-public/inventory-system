package com.example.inventory.integration;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryDocument {
    private String uuid;
    private String name;
    private long quantity;
    private BigDecimal price;
}
