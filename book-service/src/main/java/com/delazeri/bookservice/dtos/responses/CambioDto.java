package com.delazeri.bookservice.dtos.responses;

public record CambioDto(
        Long id,
        String from,
        String to,
        Double conversionFactor,
        Double convertedValue,
        String environment
) {
}