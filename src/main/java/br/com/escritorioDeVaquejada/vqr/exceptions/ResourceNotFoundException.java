package br.com.escritorioDeVaquejada.vqr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUUID=1L;
    ResourceNotFoundException(String message){
        super(message);
    }
}
