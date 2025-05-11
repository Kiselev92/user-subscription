package com.kiselev.usersubscription.adapter.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.kiselev.usersubscription.domain.PlatformEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;

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