package com.example.demo.controller;

import java.util.List;

import com.example.demo.dao.Document;
import com.example.demo.dto.request.DocumentSearchRequest;
import com.example.demo.service.DocumentSearchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentSearchController {

    private final DocumentSearchService documentSearchService;
    
    @PostMapping("/search")
    public List<Document> search(@RequestBody DocumentSearchRequest request){
        
        return documentSearchService.search(request);
    }
}
