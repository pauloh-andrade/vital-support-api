package br.com.vitalsupport.dtos;

import jakarta.validation.constraints.NotNull;

public record HistoricalReportRequestDto(
        @NotNull
        Long totalReports,
        @NotNull
        Long totalConfirmed,
        @NotNull
        Long cityId
) {
}
