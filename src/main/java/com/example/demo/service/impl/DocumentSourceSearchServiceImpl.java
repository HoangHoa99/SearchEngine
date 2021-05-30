package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentSourceSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentSourceSearchServiceImpl implements DocumentSourceSearchService {

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<String> getAllDocSource() {
        
        return documentRepository.getAllDocumentSource();
    }
    
}
