package com.kiselev.usersubscription.config;

import com.kiselev.usersubscription.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestApiExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        // Великого практического смысла не вижу, но в постановке требовалось применить логирование через @Slf4j
        log.warn("Not found exception ", ex);
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
