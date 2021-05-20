package com.example.demo.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentCreateRequest {
    
    private String documentTitle;
    private String documentDes;
    private String documentSource;
    private String dateCreate;
    private String review;
    private String downloaded;
}
