package com.example.demo.controller;

import com.example.demo.dto.request.BaseRequest;
import com.example.demo.dto.response.MasterDataSearchResponse;
import com.example.demo.service.MasterDataSearchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class MasterDataSearchController {

    private final MasterDataSearchService masterDataSearchService;
    
    @PostMapping("/master-data/search")
    public MasterDataSearchResponse search(@RequestBody BaseRequest request){

        return masterDataSearchService.search();
    }
}
