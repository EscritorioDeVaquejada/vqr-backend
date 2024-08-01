package br.com.escritorioDeVaquejada.vqr.infra.exception;

import br.com.escritorioDeVaquejada.vqr.exceptions.BadRequestException;
import br.com.escritorioDeVaquejada.vqr.exceptions.ResourceNotFoundException;
import br.com.escritorioDeVaquejada.vqr.dtos.ResponseExceptionDto;
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
    private final ResponseEntity<ResponseExceptionDto> handlerAllExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("exceptionlanced");
        return new ResponseEntity<>(new ResponseExceptionDto(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    private final ResponseEntity<ResponseExceptionDto> handlerResourceNotFoundExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("exceptionlanced");
        return new ResponseEntity<>(new ResponseExceptionDto(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    private final ResponseEntity<ResponseExceptionDto> handlerBadRequestExceptions(
            Exception exception, WebRequest webRequest){
        return new ResponseEntity<>(new ResponseExceptionDto(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
