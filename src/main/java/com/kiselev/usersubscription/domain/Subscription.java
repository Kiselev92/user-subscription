package com.kiselev.usersubscription.domain;

import lombok.With;
import lombok.Value;
import lombok.Builder;
import java.time.Instant;
import java.math.BigDecimal;

@With
@Value
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
     * Платформа, на которую подписываемся
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