package com.example.demo.service.impl;

import com.example.demo.dao.Document;
import com.example.demo.dto.ModelConverter;
import com.example.demo.dto.request.DocumentCreateRequest;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentCreateService;
import com.example.demo.util.DateTimeUtil;
import com.example.demo.util.StringUtil;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentCreateServiceImpl implements DocumentCreateService {

    private final DocumentRepository documentRepository;
    private final ModelConverter modelConverter;
    
    @Override
    public Long create(DocumentCreateRequest request) {
        
        Document document = new Document();
        modelConverter.map(request, document);
        document.setDateCreate(DateTimeUtil.parse(request.getDateCreate()));
        document.setReview(StringUtil.intValue(request.getReview()));
        document.setDownloaded(StringUtil.intValue(request.getDownloaded()));

        documentRepository.save(document);

        return document.getId();
    }
    
}
