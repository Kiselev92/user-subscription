package com.kiselev.usersubscription.domain;

import lombok.With;
import lombok.Value;
import lombok.Builder;

@With
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