package com.example.demo.repository;

import java.util.List;

import com.example.demo.dao.Document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    
    @Query(value = "SELECT DISTINCT document_source FROM documents", nativeQuery = true)
    List<String> getAllDocumentSource();
}
