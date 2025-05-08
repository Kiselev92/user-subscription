package com.kiselev.usersubscription.adapter.entity;

import com.kiselev.usersubscription.domain.PlatformEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private PlatformEnum platform;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "expired")
    private Instant expired;

    @Column(name = "created")
    private Instant created;

    @Column(name = "updated")
    private Instant updated;
}
