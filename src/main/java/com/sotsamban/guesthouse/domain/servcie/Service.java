package com.sotsamban.guesthouse.domain.servcie;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.guestservice.GuestService;
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
import java.util.List;

@Entity
@Table(name = "tb_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Service extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svc_id")
    private Integer serviceId;

    @NotBlank
    @Size(max = 100)
    @Column(name = "svc_nm", nullable = false)
    private String serviceName;

    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")  // Changed: desc → descr
    private String description;

    @Column(name = "prc", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "cat", nullable = false)
    private ServiceCategory category;

    @Column(name = "is_act")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private List<GuestService> guestServices;

    public enum ServiceCategory {
        FOOD_BEVERAGE, LAUNDRY, TRANSPORTATION, TOUR, SPA, BUSINESS, OTHER
    }
}
