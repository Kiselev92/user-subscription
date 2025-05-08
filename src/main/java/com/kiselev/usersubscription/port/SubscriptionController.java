package com.kiselev.usersubscription.port;

import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    public void add(@PathVariable("id") Long userId, @RequestBody Subscription subscription) {
        subscriptionService.add(userId, subscription);
    }

    @GetMapping("/users/{id}/subscriptions")
    public List<Subscription> getByUserId(@PathVariable("id") Long userId) {
        return subscriptionService.getByUserId(userId);
    }

    @DeleteMapping("/users/{id}/subscriptions/{subId}")
    public void delete(@PathVariable("id") Long userId, @PathVariable Long subId) {
        subscriptionService.removeSubscription(userId, subId);
    }

    @GetMapping("/subscriptions/top")
    public List<String> getTop(
        @RequestParam(required = false, defaultValue = "3") int count
    ) {
        return subscriptionService.getTopSubscriptions(count);
    }
}

