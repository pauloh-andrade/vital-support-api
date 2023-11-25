package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import oracle.sql.DATE;

public record PatientRequestDto(
        @NotBlank
        String cpf,
        @NotNull
        DATE birthDate,
        @NotBlank
        String gender,
        @NotBlank
        String phoneNumber,
        @NotBlank
        String bloodType,
        @NotBlank
        String maritalStatus
) {
}
