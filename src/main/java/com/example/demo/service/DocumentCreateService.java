package com.example.demo.service;

import com.example.demo.dto.request.DocumentCreateRequest;

public interface DocumentCreateService {
    
    Long create(DocumentCreateRequest request);
}
