package com.sotsamban.guesthouse.domain.staff;

import com.sotsamban.guesthouse.domain.expense.Expense;
import com.sotsamban.guesthouse.domain.housekeeping.Housekeeping;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import com.sotsamban.guesthouse.domain.roommaintence.RoomMaintenance;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "tb_staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    private Department department;

    @DecimalMin(value = "0.0")
    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice = BigDecimal.ZERO;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StaffStatus status = StaffStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<RoomMaintenance> maintenances;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Housekeeping> housekeepings;

    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    private List<Expense> expenses;

    public enum Department {
        FRONT_DESK, HOUSEKEEPING, MAINTENANCE, MANAGEMENT, SECURITY, FOOD_SERVICE
    }

    public enum StaffStatus {
        ACTIVE, INACTIVE, TERMINATED
    }
}

