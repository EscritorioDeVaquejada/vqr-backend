package br.com.escritorioDeVaquejada.vqr.exception;

import br.com.escritorioDeVaquejada.vqr.vo.exception.ExceptionResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private ResponseEntity<ExceptionResponseVO> handlerAllExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("Exception lanced");
        return new ResponseEntity<>(new ExceptionResponseVO(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ExceptionResponseVO> handlerResourceNotFoundExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("Exception lanced");
        return new ResponseEntity<>(new ExceptionResponseVO(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<ExceptionResponseVO> handlerBadRequestExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("Exception lanced");
        return new ResponseEntity<>(new ExceptionResponseVO(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    private ResponseEntity<ExceptionResponseVO> handlerInvalidJwtAuthenticationExceptions(
            Exception exception, WebRequest webRequest){
        logger.info("Exception lanced");
        return new ResponseEntity<>(new ExceptionResponseVO(
                new Date(),
                webRequest.getDescription(false),
                exception.getMessage()), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ExceptionResponseVO> handlerUsernameNotFoundExceptions(
            Exception exception, WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionResponseVO(
                    new Date(),
                    webRequest.getDescription(false),
                    exception.getMessage()));
    }
}
