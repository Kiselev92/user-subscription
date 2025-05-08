package com.kiselev.usersubscription.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@With
@Builder
public class Subscription {

    /**
     * ID подписки
     */
    Long id;

    /**
     * ID Пользователя
     */
    Long userId;

    /**
     * Система, на которую подписываемся
     */
    PlatformEnum platform;

    /**
     * Цена покупки подписки
     */
    BigDecimal boughtPrice;

    /**
     * Время окончания подписки.
     * Если сделать LocalDate, то для человека в Хабаровске подписка закончится раньше, чем для человека в Москве,
     * т.к. отсутствует часовой пояс, подписка в первом случае будет куплена на меньший срок, чем во второй.
     */
    Instant expired;

    /**
     * Дата создания подписки
     */
    Instant created;
}
