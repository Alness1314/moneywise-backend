package com.alness.moneywise.common.config;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(Map.of("status",ex.getStatusCode().toString(),"message", ex.getReason()), ex.getStatusCode());
    }

    /*
     * @ExceptionHandler(ResourceNotFoundException.class)
     * 
     * @ResponseStatus(HttpStatus.NOT_FOUND)
     * public ResponseEntity<String>
     * handleResourceNotFoundException(ResourceNotFoundException ex) {
     * return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
     * }
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage(), "mensaje", "Ocurrió un error en el servidor"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
