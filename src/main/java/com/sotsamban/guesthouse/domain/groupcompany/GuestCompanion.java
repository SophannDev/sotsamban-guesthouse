package com.sotsamban.guesthouse.domain.groupcompany;

import com.sotsamban.guesthouse.domain.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_guest_companion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestCompanion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companion_id")
    private Integer companionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Min(0)
    @Column(name = "age")
    private Integer age;

    @Size(max = 50)
    @Column(name = "relationship")
    private String relationship;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
