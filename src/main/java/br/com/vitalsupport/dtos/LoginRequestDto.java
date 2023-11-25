package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
