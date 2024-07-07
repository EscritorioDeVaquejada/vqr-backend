package br.com.escritorioDeVaquejada.vqr.exceptions;

import java.util.Date;

public record ResponseException(Date timestamp, String details, String message) {

}
