package com.kiselev.usersubscription.domain;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    int id;

    String platform;

    LocalDate startDate;

    BigDecimal price;

    public static Subscription fromSubscriptionEntity(SubscriptionEntity entity) {
        return Subscription.builder()
                .id(entity.getId())
                .platform(entity.getServiceName())
                .startDate(entity.getStartDate())
                .price(entity.getPrice())
                .build();
    }

    public SubscriptionEntity toSubscriptionEntity() {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setServiceName(platform);
        entity.setStartDate(startDate);
        entity.setPrice(price);
        return entity;
    }
}
