package br.com.escritorioDeVaquejada.vqr.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMethod {
    CREDIT,
    DEBIT,
    PIX,
    CHECK,
    CASH;

    @JsonCreator
    public static PaymentMethod fromString(String value) {
        return PaymentMethod.valueOf(value.toUpperCase());
    }
}
