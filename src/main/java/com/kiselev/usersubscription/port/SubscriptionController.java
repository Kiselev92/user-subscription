package com.kiselev.usersubscription.port;

import com.kiselev.usersubscription.domain.Subscription;
import com.kiselev.usersubscription.domain.TopSubscription;
import com.kiselev.usersubscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    public void add(@PathVariable int id, @RequestBody Subscription subscription) {
        subscriptionService.add(id, subscription);
    }

    @GetMapping("/users/{id}/subscriptions")
    public List<Subscription> get(@PathVariable int id) {
        return subscriptionService.getByUserId(id);
    }

    @DeleteMapping("/users/{id}/subscriptions/{subId}")
    public void delete(@PathVariable int id, @PathVariable int subId) {
        subscriptionService.removeSubscription(id, subId);
    }

    @GetMapping("/users/{id}/subscriptions/top")
    public List<TopSubscription> getTop() {
        return subscriptionService.getTopSubscriptions();
    }
}

