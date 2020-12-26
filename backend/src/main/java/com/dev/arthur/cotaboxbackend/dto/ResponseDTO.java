package com.dev.arthur.cotaboxbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO<T> {
    private String message;

    private T body;
}
