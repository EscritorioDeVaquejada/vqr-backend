package br.com.escritorioDeVaquejada.vqr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@ControllerAdvice()
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    private final ResponseEntity<ResponseException> handlerAllExceptions(Exception exception, WebRequest webRequest){
        logger.info("exceptionlanced");
        return new ResponseEntity<>(new ResponseException(new Date(),webRequest.getDescription(false),exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
