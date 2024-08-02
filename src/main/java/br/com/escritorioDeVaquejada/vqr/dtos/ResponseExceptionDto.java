package br.com.escritorioDeVaquejada.vqr.dtos;

import java.util.Date;

public record ResponseExceptionDto(
        Date timestamp,
        String details,
        String message) { }
