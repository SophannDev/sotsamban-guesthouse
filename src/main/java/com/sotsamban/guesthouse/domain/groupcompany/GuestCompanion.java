package com.sotsamban.guesthouse.domain.groupcompany;

import com.sotsamban.guesthouse.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "tb_guest_companion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestCompanion extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comp_id")
    private Integer companionId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "fname", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "lname", nullable = false)
    private String lastName;

    @Min(0)
    @Column(name = "age")
    private Integer age;

    @Size(max = 50)
    @Column(name = "rel")
    private String relationship;
}
