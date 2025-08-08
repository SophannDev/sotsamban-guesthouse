package com.sotsamban.guesthouse.domain.guest;

import com.sotsamban.guesthouse.config.ImageConfig;
import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.feedback.Feedback;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "id_doc_type")
    private IdDocumentType idDocumentType;

    @Size(max = 50)
    @Column(name = "id_doc_num")
    private String idDocumentNumber;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    @Convert(converter = ImageConfig.StringListConverter.class)
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> images = new ArrayList<>();

    public enum IdDocumentType {
        PASSPORT, DRIVING_LICENSE, NATIONAL_ID, OTHER
    }
}
