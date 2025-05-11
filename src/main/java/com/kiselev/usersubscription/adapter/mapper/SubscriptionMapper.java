package com.kiselev.usersubscription.adapter.mapper;

import org.mapstruct.Mapper;
import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;

@Mapper
public interface SubscriptionMapper {

    Subscription toDomain(SubscriptionEntity entity);

    SubscriptionEntity toEntity(Subscription domain);
}
