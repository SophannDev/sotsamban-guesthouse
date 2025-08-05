package com.sotsamban.guesthouse.domain.housekeeping;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.staff.Staff;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_housekeeping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Housekeeping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hk_id")
    private Integer housekeepingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "clean_dt")
    private LocalDateTime cleaningDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "clean_type", nullable = false)
    private CleaningType cleaningType;

    @Enumerated(EnumType.STRING)
    @Column(name = "sts")
    private HousekeepingStatus status = HousekeepingStatus.PENDING;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Min(1)
    @Max(10)
    @Column(name = "insp_score")
    private Integer inspectionScore;

    public enum CleaningType {
        CHECKOUT_CLEANING, MAINTENANCE_CLEANING, DEEP_CLEANING, INSPECTION
    }

    public enum HousekeepingStatus {
        PENDING, IN_PROGRESS, COMPLETED, FAILED_INSPECTION
    }
}