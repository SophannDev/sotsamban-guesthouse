package com.sotsamban.guesthouse.domain.roomtype;

import com.sotsamban.guesthouse.domain.BaseEntity;
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
public class RoomType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Long roomTypeId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "type_nm", nullable = false, unique = true)
    private String typeName;

    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "base_rt", nullable = false)
    private BigDecimal baseRate = BigDecimal.ZERO;

    @Min(1)
    @Column(name = "std_occ", nullable = false)
    private Integer standardOccupancy = 1;

    @Min(1)
    @Column(name = "max_occ", nullable = false)
    private Integer maxOccupancy = 1;

    @Column(name = "amts", columnDefinition = "TEXT")
    private String amenities;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
}
