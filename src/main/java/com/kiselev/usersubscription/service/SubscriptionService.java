package com.kiselev.usersubscription.service;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.mapper.SubscriptionMapper;
import com.kiselev.usersubscription.adapter.repository.SubscriptionRepository;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.common.exception.AlreadySubscribedException;
import com.kiselev.usersubscription.common.exception.NotFoundException;
import com.kiselev.usersubscription.domain.PlatformEnum;
import com.kiselev.usersubscription.domain.Subscription;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper = Mappers.getMapper(SubscriptionMapper.class);

    @Transactional
    public void add(Long userId, Subscription subscription) {
        UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("user %d not found".formatted(userId)));

        boolean alreadySubscribed = userEntity.getSubscriptions().stream()
                .anyMatch(sub -> sub.getPlatform() == subscription.getPlatform());

        if (alreadySubscribed) {
            throw new AlreadySubscribedException(
                "User %d already subscribed to %s".formatted(userId, subscription.getPlatform().getTitle()));
        }
        
        SubscriptionEntity entity = subscriptionMapper.toEntity(subscription);
        entity.setUser(userEntity);
        subscriptionRepository.save(entity);
    }

    public List<Subscription> getByUserId(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("user %d not found".formatted(userId)));

        return userEntity.getSubscriptions().stream()
                .map(subscriptionMapper::toDomain)
                .toList();
    }

    public void removeSubscription(Long userId, Long subId) {

        SubscriptionEntity entity = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NotFoundException("Subscription %d not found".formatted(subId)));

        if(!entity.getUser().getId().equals(userId)) {
            throw new NotFoundException("User %d is not subscribed".formatted(userId));
        }
        subscriptionRepository.delete(entity);
    }

    public List<String> getTopSubscriptions(int count) {
        return subscriptionRepository.getTopSubscriptions(count).stream().map(PlatformEnum::getTitle).toList();
    }
}
