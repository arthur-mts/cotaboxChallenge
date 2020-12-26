package com.dev.arthur.cotaboxbackend.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ParticipationDTO {
    private String id;

    @NotBlank(message = "O nome é obrigatório!")
    private String firstName;

    @NotBlank(message = "O sobrenome é obrigatório!")
    private String lastName;

    @NotNull(message = "A participação é obrigatória!")
    @Min(value = 0, message = "A porcentagem deve ser maior que zero!")
    @Max(value = 100, message = "A porcentagem não deve ser maior que cem!s")
    private Float participation;
}
