package com.kiselev.usersubscription.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlatformEnum {
    YOUTUBE_PREMIUM("Youtube Premium"),
    VK_MUSIC("ВК Музыка"),
    YANDEX_PLUS("Яндекс Плюс"),
    NETFLIX("Netflix");

    private final String title;
}