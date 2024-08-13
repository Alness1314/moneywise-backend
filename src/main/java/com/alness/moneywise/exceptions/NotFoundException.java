package com.alness.moneywise.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class NotFoundException extends RuntimeException{
    private String code;
    
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(String code, String message){
        super(message);
        this.code = code;
    }
}
