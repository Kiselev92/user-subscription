package com.kiselev.usersubscription.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
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