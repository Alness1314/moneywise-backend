package com.alness.moneywise.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatters {

    private Formatters(){
        throw new IllegalStateException("Utility class");
    }
    
    public static LocalDateTime parseLocalDateTime(String stringDateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(stringDateTime, formatter);
	}


    public static LocalDate parseLocalDate(String stringDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(stringDate, formatter);
	}
}
