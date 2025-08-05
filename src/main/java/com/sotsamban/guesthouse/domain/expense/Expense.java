package com.sotsamban.guesthouse.domain.expense;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.staff.Staff;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private Integer expenseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "exp_cat", nullable = false)
    private ExpenseCategory expenseCategory;


    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")  // Changed: desc → descr
    private String description;

    @DecimalMin(value = "0.01")
    @Column(name = "amt", nullable = false)
    private BigDecimal amount;

    @Column(name = "exp_dt")
    private LocalDateTime expenseDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Size(max = 50)
    @Column(name = "rec_num")
    private String receiptNumber;

    @Size(max = 100)
    @Column(name = "vendor")
    private String vendor;

    public enum ExpenseCategory {
        UTILITIES, MAINTENANCE, SUPPLIES, MARKETING, STAFF, FOOD_BEVERAGE, INSURANCE, TAXES, OTHER
    }
}