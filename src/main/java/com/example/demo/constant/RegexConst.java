package com.example.demo.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexConst {
    
    public static final String NUMBERIC = "^(0|[1-9][0-9]*)$";
    public static final String SLASH_REGEX = "\\";
}
