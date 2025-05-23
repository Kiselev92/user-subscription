package com.kiselev.usersubscription.adapter.entity;

import lombok.Data;
import java.time.Instant;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.kiselev.usersubscription.domain.PlatformEnum;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
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