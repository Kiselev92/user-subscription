package com.kiselev.usersubscription.domain;

import lombok.Value;

/**
 * Возвращаем топ подписок (просто value объект из подписок).
 */
@Value
public class TopSubscription {

    String serviceName;

    int subscriptionCount;
}

