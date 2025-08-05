package com.sotsamban.guesthouse.domain.roomamenitymapping;

import com.sotsamban.guesthouse.domain.BaseEntity;
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
public class RoomAmenityMapping extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amt_id", nullable = false)
    private RoomAmenity amenity;

    @Min(1)
    @Column(name = "qty")
    private Integer quantity = 1;
}

