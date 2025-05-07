package com.kiselev.usersubscription.domain;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class User {

    int id;

    String name;

    String email;


    List<Subscription> subscriptions;

    public static User fromUserEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .subscriptions(entity.getSubscriptions().stream()
                        .map(Subscription::fromSubscriptionEntity)
                        .toList()
                )
                .build();
    }

    public UserEntity toUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setEmail(email);
        entity.setSubscriptions(
                subscriptions != null ? subscriptions.stream()
                        .map(sub -> {
                            SubscriptionEntity subscriptionEntity = sub.toSubscriptionEntity();
                            subscriptionEntity.setUser(entity);
                            return subscriptionEntity;
                        }).toList()
                        : new ArrayList<>()
        );
        return entity;
    }
}