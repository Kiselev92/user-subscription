package com.kiselev.usersubscription.service;

import com.kiselev.usersubscription.AbstractIntegrationTest;
import com.kiselev.usersubscription.adapter.entity.SubscriptionEntity;
import com.kiselev.usersubscription.adapter.entity.UserEntity;
import com.kiselev.usersubscription.adapter.repository.SubscriptionRepository;
import com.kiselev.usersubscription.adapter.repository.UserRepository;
import com.kiselev.usersubscription.common.exception.AlreadySubscribedException;
import com.kiselev.usersubscription.common.exception.NotFoundException;
import com.kiselev.usersubscription.domain.PlatformEnum;
import com.kiselev.usersubscription.domain.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SubscriptionServiceTest extends AbstractIntegrationTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    private UserEntity testUser;
    private Subscription testSubscription;

    @BeforeEach
    void set_up() {
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();

        testUser = new UserEntity();
        testUser.setName("test_user");
        testUser.setEmail("test_email");
        testUser = userRepository.save(testUser);

        testSubscription = Subscription.builder()
                .platform(PlatformEnum.NETFLIX)
                .boughtPrice(new BigDecimal("9.99"))
                .expired(Instant.now().plus(30, ChronoUnit.DAYS))
                .created(Instant.now())
                .build();
    }

    @Test
    void create_subscription_does_not_exist() {
        // WHEN
        subscriptionService.add(testUser.getId(), testSubscription);

        // THEN
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
        assertThat(subscriptions)
                .hasSize(1)
                .first()
                .satisfies(sub -> {
                    assertThat(sub.getPlatform()).isEqualTo(PlatformEnum.NETFLIX);
                    assertThat(sub.getUser().getId()).isEqualTo(testUser.getId());
                });
    }

    @Test
    void add_subscription_for_non_existent_user() {
        // GIVEN
        Long nonExistentUserId = 999L;

        // WHEN & THEN
        assertThatThrownBy(() -> subscriptionService.add(nonExistentUserId, testSubscription))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("user 999 not found");
    }

    @Test
    void add_duplicate_subscription() {
        // GIVEN
        subscriptionService.add(testUser.getId(), testSubscription);

        Subscription duplicateSubscription = Subscription.builder()
                .platform(PlatformEnum.NETFLIX) // Та же платформа
                .boughtPrice(new BigDecimal("8.99"))
                .expired(Instant.now().plus(40, ChronoUnit.DAYS))
                .created(Instant.now())
                .build();

        // WHEN & THEN
        assertThatThrownBy(() -> subscriptionService.add(testUser.getId(), duplicateSubscription))
                .isInstanceOf(AlreadySubscribedException.class)
                .hasMessageContaining("already subscribed to Netflix");
    }
}