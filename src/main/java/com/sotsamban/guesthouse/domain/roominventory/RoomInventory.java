package com.sotsamban.guesthouse.domain.roominventory;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.inventory.Inventory;
import com.sotsamban.guesthouse.domain.room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_room_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rinv_id")
    private Integer roomInventoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inv_id", nullable = false)
    private Inventory inventory;

    @Min(1)
    @Column(name = "qty_asgn", nullable = false)
    private Integer quantityAssigned = 1;

    @Column(name = "asgn_dt")
    private LocalDateTime assignedDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "cond_sts")
    private ConditionStatus conditionStatus = ConditionStatus.GOOD;

    public enum ConditionStatus {
        EXCELLENT, GOOD, FAIR, POOR, NEEDS_REPLACEMENT
    }
}
