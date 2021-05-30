package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.DocumentUpdateRequest;
import com.example.demo.dto.response.DocumentUpdateResponse;
import com.example.demo.service.DocumentUpdateService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentUpdateController {

    private final DocumentUpdateService documentUpdateService;
    
    @PostMapping(value="/update")
    public DocumentUpdateResponse postMethodName(@RequestBody DocumentUpdateRequest request) {
        return documentUpdateService.update(request);
    }
    
}
