package com.sotsamban.guesthouse.domain.roomamenitymapping;

import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.roomamenity.RoomAmenity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_room_amenity_mapping",
        uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "amenity_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomAmenityMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Integer mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", nullable = false)
    private RoomAmenity amenity;

    @Min(1)
    @Column(name = "quantity")
    private Integer quantity = 1;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

