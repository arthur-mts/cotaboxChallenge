package com.dev.arthur.cotaboxbackend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@ToString
public class ParticipationDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório!")
    private String lastName;

    @NotNull(message = "A participação é obrigatória!")
    @Min(value = 0, message = "A porcentagem deve ser maior que zero!")
    @Max(value = 100, message = "A porcentagem não deve ser maior que cem!")
    private Float participationPercentage;
}
