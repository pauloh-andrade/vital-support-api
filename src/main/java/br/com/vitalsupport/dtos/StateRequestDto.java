package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StateRequestDto(
        @NotBlank
        String name
) {
}
