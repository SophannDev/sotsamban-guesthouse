package com.sotsamban.guesthouse.domain.feedback;

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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Min(1)
    @Max(5)
    @Column(name = "overall_rating", nullable = false)
    private Integer overallRating;

    @Min(1)
    @Max(5)
    @Column(name = "room_rating")
    private Integer roomRating;

    @Min(1)
    @Max(5)
    @Column(name = "service_rating")
    private Integer serviceRating;

    @Min(1)
    @Max(5)
    @Column(name = "cleanliness_rating")
    private Integer cleanlinessRating;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "feedback_date")
    private LocalDateTime feedbackDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FeedbackStatus status = FeedbackStatus.PENDING;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum FeedbackStatus {
        PENDING, REVIEWED, RESPONDED
    }
}
