package com.kiselev.usersubscription.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Настройка для работы с Java 8 Time API
        objectMapper.registerModule(new JavaTimeModule());

        // Игнорировать отсутствующие свойства (если JSON не содержит всех полей объекта)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Игнорировать неизвестные свойства (если в JSON есть поля, которых нет в классе)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Дополнительные полезные настройки
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return objectMapper;
    }
}
