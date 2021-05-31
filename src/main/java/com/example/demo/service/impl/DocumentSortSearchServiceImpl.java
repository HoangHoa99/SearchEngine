package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.constant.SortFieldConst;
import com.example.demo.service.DocumentSortSearchService;

import org.springframework.stereotype.Service;

@Service
public class DocumentSortSearchServiceImpl implements DocumentSortSearchService{

    @Override
    public List<String> getAllSortField() {

        List<String> returnList = new ArrayList<>();
        returnList.add(SortFieldConst.CLICK_COUNT);
        returnList.add(SortFieldConst.REVIEW);
        returnList.add(SortFieldConst.DOWNLOADED);
        returnList.add(SortFieldConst.DATE_CREATE);

        return returnList;
    }
    
}
