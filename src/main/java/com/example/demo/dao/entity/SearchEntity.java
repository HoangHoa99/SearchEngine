package com.example.demo.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SearchEntity {
    
    String query;
    Integer page;
    String sortField;
    String sortType;
}
