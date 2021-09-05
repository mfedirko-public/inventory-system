package com.example.inventory.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryDTO {
    private String uuid;
    private String name;
    private long quantity;
    private BigDecimal price;
}
