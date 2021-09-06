package com.example.inventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Data
@Table("INVENTORY")
public class InventoryDTO {
    @Id
    @Column("ID")
    private Integer id;
    @Column("UUID")
    private String uuid;
    @Column("NAME")
    private String name;
    @Column("QUANTITY")
    private long quantity;
    @Column("PRICE")
    private BigDecimal price;
}
