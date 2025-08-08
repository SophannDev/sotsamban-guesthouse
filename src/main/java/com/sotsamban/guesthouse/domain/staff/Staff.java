package com.sotsamban.guesthouse.domain.staff;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.expense.Expense;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staffId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @ElementCollection
    @CollectionTable(name = "staff_images", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

}

