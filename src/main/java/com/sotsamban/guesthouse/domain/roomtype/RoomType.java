package com.sotsamban.guesthouse.domain.roomtype;

import com.sotsamban.guesthouse.domain.room.Room;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_room_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private Integer roomTypeId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @DecimalMin(value = "0.0")
    @Column(name = "base_rate", nullable = false)
    private BigDecimal baseRate = BigDecimal.ZERO;

    @Min(1)
    @Column(name = "standard_occupancy", nullable = false)
    private Integer standardOccupancy = 1;

    @Min(1)
    @Column(name = "max_occupancy", nullable = false)
    private Integer maxOccupancy = 1;

    @Column(name = "amenities", columnDefinition = "TEXT")
    private String amenities;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
