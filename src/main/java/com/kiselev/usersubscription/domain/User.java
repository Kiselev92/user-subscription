package com.kiselev.usersubscription.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    /**
     * ID пользвоателя
     */
    Long id;

    /**
     * Имя пользователя
     */
    String name;

    /**
     * Емэйл пользователя.
     */
    String email;
}