package com.example.demo.service;

import com.example.demo.dto.request.DocumentUpdateRequest;
import com.example.demo.dto.response.DocumentUpdateResponse;

public interface DocumentUpdateService {
    
    DocumentUpdateResponse update(DocumentUpdateRequest request);
}
