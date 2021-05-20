package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.demo.constant.Character;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtil {
    
    /**
     * get localDate of dateCreate
     * 
     * @param date
     * @return dateCreate as {@code LocalDate}
     */
    public static LocalDate parse(String date){
        if(StringUtils.isNotBlank(date)){
            String dateConvert = date.replace(Character.SLASH, Character.HYPHEN);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            return LocalDate.parse(dateConvert, formatter);
        }
        else{
            return LocalDate.now();
        }
    }
}
