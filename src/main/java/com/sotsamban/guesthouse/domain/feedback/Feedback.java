package com.sotsamban.guesthouse.domain.feedback;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.guest.Guest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "tb_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdb_id")
    private Integer feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bkg_id", nullable = false)
    private Booking booking;

    @Min(1)
    @Max(5)
    @Column(name = "ovr_rate", nullable = false)
    private Integer overallRating;

    @Min(1)
    @Max(5)
    @Column(name = "room_rate")
    private Integer roomRating;

    @Min(1)
    @Max(5)
    @Column(name = "svc_rate")
    private Integer serviceRating;

    @Min(1)
    @Max(5)
    @Column(name = "clean_rate")
    private Integer cleanlinessRating;

    @Column(name = "comm", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "fdb_dt")
    private LocalDateTime feedbackDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "sts")
    private FeedbackStatus status = FeedbackStatus.PENDING;

    public enum FeedbackStatus {
        PENDING, REVIEWED, RESPONDED
    }
}
