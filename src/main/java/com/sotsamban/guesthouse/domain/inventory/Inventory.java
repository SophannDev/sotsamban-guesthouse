package com.sotsamban.guesthouse.domain.inventory;

import com.sotsamban.guesthouse.domain.roominventory.RoomInventory;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private InventoryCategory category;

    @Min(0)
    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable = 0;

    @Min(0)
    @Column(name = "minimum_threshold")
    private Integer minimumThreshold = 0;

    @DecimalMin(value = "0.0")
    @Column(name = "unit_cost")
    private BigDecimal unitCost = BigDecimal.ZERO;

    @Size(max = 100)
    @Column(name = "supplier")
    private String supplier;

    @Column(name = "last_restocked")
    private LocalDateTime lastRestocked;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private List<RoomInventory> roomInventories;

    public enum InventoryCategory {
        LINENS, TOILETRIES, CLEANING_SUPPLIES, FURNITURE, ELECTRONICS, MAINTENANCE, FOOD_BEVERAGE, OTHER
    }
}
