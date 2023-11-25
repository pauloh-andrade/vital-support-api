package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequestDto(
        @NotBlank
        int number,
        @NotBlank
        String streetName,
        @NotBlank
        String cep,
        @NotNull
        Long cityId
) {
}