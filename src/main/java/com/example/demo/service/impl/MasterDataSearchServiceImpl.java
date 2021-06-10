package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.dto.response.MasterDataSearchResponse;
import com.example.demo.service.DocumentSortSearchService;
import com.example.demo.service.DocumentSourceSearchService;
import com.example.demo.service.MasterDataSearchService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterDataSearchServiceImpl implements MasterDataSearchService {

    // ANCHOR - Declare service
    private final DocumentSourceSearchService documentSourceSearchService;
    private final DocumentSortSearchService documentSortSearchService;

    @Override
    public MasterDataSearchResponse search() {
        
        /** SECTION - document source */
        List<String> docSources = documentSourceSearchService.getAllDocSource();

        /** SECTION - document sort */
        List<String> docSorts = documentSortSearchService.getAllSortField();
        
        MasterDataSearchResponse response = new MasterDataSearchResponse();
        response.setDocSources(docSources);
        response.setDocSorts(docSorts);

        return response;
    }
    
}
