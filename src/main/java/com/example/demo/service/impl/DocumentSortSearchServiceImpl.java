package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.constant.SortField;
import com.example.demo.service.DocumentSortSearchService;

import org.springframework.stereotype.Service;

@Service
public class DocumentSortSearchServiceImpl implements DocumentSortSearchService{

    @Override
    public List<String> getAllSortField() {
        return List.of(SortField.CLICK_COUNT, SortField.REVIEW, SortField.DOWNLOADED, SortField.DATE_CREATE);
    }
    
}
