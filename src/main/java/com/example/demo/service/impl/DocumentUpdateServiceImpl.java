package com.example.demo.service.impl;


import com.example.demo.dao.Document;
import com.example.demo.dto.request.DocumentUpdateRequest;
import com.example.demo.dto.response.DocumentUpdateResponse;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentUpdateServiceImpl implements DocumentUpdateService {

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public DocumentUpdateResponse update(DocumentUpdateRequest request) {
        
        Document document = documentRepository.getById(request.getId());

        int clickCount = document.getClickCount() + 1;

        document.setClickCount(clickCount);

        documentRepository.save(document);

        DocumentUpdateResponse response = new DocumentUpdateResponse();
        response.setId(document.getId());

        return response;
    }
    
}
