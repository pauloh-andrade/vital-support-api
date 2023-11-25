package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotNull;

public record SymptomHistoryRequestDto(
        boolean statusMeningitis,

        @NotNull
        Long patientId
) {
}
