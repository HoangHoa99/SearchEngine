package com.example.demo.dto.request;

import com.example.demo.constant.SortField;
import com.example.demo.constant.SortType;
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

        private String sortField = SortField.DATE_CREATE;

        private String sortType = SortType.ASC;
    }
}
