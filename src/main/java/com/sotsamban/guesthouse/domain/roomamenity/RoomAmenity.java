package com.sotsamban.guesthouse.domain.roomamenity;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.roomamenitymapping.RoomAmenityMapping;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tb_room_amenity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomAmenity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amt_id")
    private Integer amenityId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "amt_nm", nullable = false, unique = true)
    private String amenityName;

    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")  // Changed: desc → descr
    private String description;

    @Column(name = "is_act")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "amenity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoomAmenityMapping> roomMappings;
}
