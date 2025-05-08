package com.kiselev.usersubscription.adapter.mapper;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface SubscriptionMapper {

    Subscription toDomain(SubscriptionEntity entity);

    SubscriptionEntity toEntity(Subscription domain);
}
