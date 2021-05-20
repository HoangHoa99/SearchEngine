package com.example.demo.util;

import com.example.demo.constant.RegexConst;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    /**
     * get int value of input
     * 
     * @param value
     * @return int value
     */
    public static int intValue(String value){
        if(StringUtils.isNotBlank(value) && (value.matches(RegexConst.NUMBERIC))){
            return Integer.valueOf(value);
        }
        else{
            return 0;
        }
    }    
}
