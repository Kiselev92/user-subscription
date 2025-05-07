package com.kiselev.usersubscription.service;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.repository.SubscriptionRepository;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.domain.TopSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public void add(int userId, Subscription subscription) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user not found"));
        SubscriptionEntity entity = subscription.toSubscriptionEntity();
        entity.setUser(userEntity);
        subscriptionRepository.save(entity);
    }

    public List<Subscription> getByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("user not found"));
        return userEntity.getSubscriptions().stream()
                .map(Subscription::fromSubscriptionEntity)
                .toList();
    }

    public void removeSubscription(int userId, int subId) {
        SubscriptionEntity entity = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));
        if(entity.getUser().getId() != userId) {
            throw new NoSuchElementException("User is not subscribed");
        }
        subscriptionRepository.delete(entity);
    }

    public List<TopSubscription> getTopSubscriptions() {
        return subscriptionRepository.getTopSubscriptions();
    }
}
