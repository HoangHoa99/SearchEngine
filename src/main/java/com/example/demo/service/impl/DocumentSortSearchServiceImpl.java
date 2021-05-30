package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.constant.SortField;
import com.example.demo.service.DocumentSortSearchService;

import org.springframework.stereotype.Service;

@Service
public class DocumentSortSearchServiceImpl implements DocumentSortSearchService{

    @Override
    public List<String> getAllSortField() {

        List<String> returnList = new ArrayList<>();
        returnList.add(SortField.CLICK_COUNT);
        returnList.add(SortField.REVIEW);
        returnList.add(SortField.DOWNLOADED);
        returnList.add(SortField.DATE_CREATE);

        return returnList;
    }
    
}
