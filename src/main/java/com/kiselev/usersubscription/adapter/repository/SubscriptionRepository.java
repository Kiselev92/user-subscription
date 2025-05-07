package com.kiselev.usersubscription.adapter.repository;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.domain.TopSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {

    @Query(value = """
        SELECT s.service_name AS serviceName, COUNT(s.user_id) AS subscriptionCount
        FROM subscriptions s
        GROUP BY s.service_name
        ORDER BY subscriptionCount DESC
        LIMIT 3
        """, nativeQuery = true)
    List<TopSubscription> getTopSubscriptions();
}

