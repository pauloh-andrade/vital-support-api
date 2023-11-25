package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String permission
) {
}
