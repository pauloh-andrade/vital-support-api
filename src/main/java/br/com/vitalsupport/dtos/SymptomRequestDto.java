package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SymptomRequestDto (
    @NotBlank
    String name,
    @NotNull
    Long symptomHistoryId
) { }
