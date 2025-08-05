package com.sotsamban.guesthouse.domain.guest;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.feedback.Feedback;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_guest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Integer guestId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "fname", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "lname", nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @Column(name = "addr", columnDefinition = "TEXT")
    private String address;

    @Size(max = 50)
    @Column(name = "city")
    private String city;

    @Size(max = 50)
    @Column(name = "state")
    private String state;

    @Size(max = 50)
    @Column(name = "country")
    private String country;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_doc_type")
    private IdDocumentType idDocumentType;

    @Size(max = 50)
    @Column(name = "id_doc_num")
    private String idDocumentNumber;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    public enum IdDocumentType {
        PASSPORT, DRIVING_LICENSE, NATIONAL_ID, OTHER
    }
}
