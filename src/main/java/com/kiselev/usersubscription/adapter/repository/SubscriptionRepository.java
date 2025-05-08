package com.kiselev.usersubscription.adapter.repository;

import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.domain.PlatformEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query(value = """
        SELECT platform, COUNT(user_id) FROM subscriptions
        GROUP BY platform
        ORDER BY 2 DESC
        LIMIT :count
        """, nativeQuery = true)
    List<PlatformEnum> getTopSubscriptions(@Param("count") Integer count);
}

