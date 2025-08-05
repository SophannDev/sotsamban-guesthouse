package com.sotsamban.guesthouse.domain.room;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.housekeeping.Housekeeping;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import com.sotsamban.guesthouse.domain.roomamenitymapping.RoomAmenityMapping;
import com.sotsamban.guesthouse.domain.roominventory.RoomInventory;
import com.sotsamban.guesthouse.domain.roommaintence.RoomMaintenance;
import com.sotsamban.guesthouse.domain.roomtype.RoomType;
import com.sotsamban.guesthouse.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_room")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @NotBlank
    @Size(max = 10)
    @Column(name = "room_num", nullable = false, unique = true)
    private String roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Convert(converter = RoomStatus.Converter.class)
    @Column(name = "sts")
    private RoomStatus status = RoomStatus.AVAILABLE;

    @Column(name = "base_prc", nullable = false)
    private BigDecimal basePrice = BigDecimal.ZERO;

    @Min(1)
    @Column(name = "max_occ", nullable = false)
    private Integer maxOccupancy = 1;


    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")  // Changed: desc → descr
    private String description;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomMaintenance> maintenances;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Housekeeping> housekeepings;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomAmenityMapping> amenityMappings;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomInventory> roomInventories;

}
