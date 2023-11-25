package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClinicRequestDto (
        @NotBlank
        String name,
        @NotBlank
        String socialReason,
        @NotNull
        Long cnpj,
        @NotBlank
        String oppeningHours,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String email,
        @NotNull
        Long addressId
) {
}
