package com.sotsamban.guesthouse.domain.roominventory;

import com.sotsamban.guesthouse.domain.inventory.Inventory;
import com.sotsamban.guesthouse.domain.room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_room_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_inventory_id")
    private Integer roomInventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Min(1)
    @Column(name = "quantity_assigned", nullable = false)
    private Integer quantityAssigned = 1;

    @Column(name = "assigned_date")
    private LocalDateTime assignedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_status")
    private ConditionStatus conditionStatus = ConditionStatus.GOOD;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum ConditionStatus {
        EXCELLENT, GOOD, FAIR, POOR, NEEDS_REPLACEMENT
    }
}
