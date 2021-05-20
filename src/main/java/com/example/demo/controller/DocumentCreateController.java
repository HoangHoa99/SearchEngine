package com.example.demo.controller;

import com.example.demo.dto.request.DocumentCreateRequest;
import com.example.demo.dto.response.DocumentCreateResponse;
import com.example.demo.service.DocumentCreateService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentCreateController {

    private final DocumentCreateService documentCreateService;
    
    @PostMapping("/create")
    public DocumentCreateResponse create(@RequestBody DocumentCreateRequest request){

        Long docId = documentCreateService.create(request);

        DocumentCreateResponse response = new DocumentCreateResponse();
        response.setId(docId);
        
        return response;
    }
}
