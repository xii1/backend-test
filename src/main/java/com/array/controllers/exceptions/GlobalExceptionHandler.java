package com.array.controllers.exceptions;

import com.array.controllers.responses.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author XIII
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        final String message = ex.getBindingResult().getFieldErrors()
                .stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).get();

        return ResponseEntity.badRequest().body(ApiResponse.getBuilder()
                .status(HttpStatus.BAD_REQUEST.value()).message(message).error("Bad Request").build());
    }
}
