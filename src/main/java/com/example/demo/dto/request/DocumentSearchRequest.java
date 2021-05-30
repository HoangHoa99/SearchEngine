package com.example.demo.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentSearchRequest {
    
    private String queryString;

    private DocumentSort sort;

    private String filter;

    @Data
    @EqualsAndHashCode(callSuper = false)
    public class DocumentSort{

        private String sortField;

        private String sortType;
    }

    private String page;
}
