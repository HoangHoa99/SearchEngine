package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.Document;
import com.example.demo.dto.request.DocumentSearchRequest;

public interface DocumentSearchService {
    
    public List<Document> getAll();

    public List<Document> search(DocumentSearchRequest request);
}
