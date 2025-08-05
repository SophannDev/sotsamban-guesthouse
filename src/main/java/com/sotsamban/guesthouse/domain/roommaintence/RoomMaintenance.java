package com.sotsamban.guesthouse.domain.roommaintence;
import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.staff.Staff;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_room_maintenance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMaintenance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mnt_id")
    private Integer maintenanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "mnt_type", nullable = false)
    private MaintenanceType maintenanceType;

    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")  // Changed: desc → descr
    private String description;

    @NotNull
    @Column(name = "sched_dt", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "comp_dt")
    private LocalDateTime completedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sts")
    private MaintenanceStatus status = MaintenanceStatus.SCHEDULED;

    @Column(name = "cost")
    private BigDecimal cost = BigDecimal.ZERO;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public enum MaintenanceType {
        PREVENTIVE, CORRECTIVE, EMERGENCY, INSPECTION
    }

    public enum MaintenanceStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
