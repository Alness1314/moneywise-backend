package com.alness.moneywise.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalExceptionHandler extends RuntimeException{
    private String code;
    private HttpStatus status;

    public GlobalExceptionHandler(String code, HttpStatus status, String message){
        super(message);
        this.code = code;
        this.status = status;
    }
}
