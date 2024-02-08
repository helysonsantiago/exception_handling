package com.dentalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .isEmpty() ?
                "Erro desconhecido" :
                ex.getBindingResult()
                        .getAllErrors()
                        .get(0)
                        .getDefaultMessage();


        return DefaultResponse.buildDefaultResponse(HttpStatus.BAD_REQUEST ,
                                                    errorMessage ,
                                                    true ,
                                                    null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return DefaultResponse.buildDefaultResponse(HttpStatus.FORBIDDEN ,
                                                    "Você não tem permissão para acessar este recurso." ,
                                                    true ,
                                                    null);

    }
}
