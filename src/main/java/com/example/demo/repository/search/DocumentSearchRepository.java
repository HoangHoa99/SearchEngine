package com.example.demo.repository.search;

import com.example.demo.dao.Document;
import com.example.demo.dao.entity.SearchEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class DocumentSearchRepository {

    @Autowired
    ObjectMapper objectMapper;
    
    String[] fields = {"documentTitle", "documentDes"};

    @PersistenceContext
    private EntityManager entityManager;

    FullTextEntityManager fullTextEntityManager;


    public void indexing() {
        if (fullTextEntityManager == null) return;
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Document> search(String text) throws JsonMappingException, JsonProcessingException {

        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        // indexing();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Document.class).get();

        SearchEntity searchEntity = new ObjectMapper().readValue(text, SearchEntity.class);

        Query query = queryBuilder
                .keyword()
                .onFields(fields)
                .matching("*" + searchEntity.getQuery().toLowerCase() + "*")
                .createQuery();

        Integer firstResult = searchEntity.getPage() - 1;
        if(firstResult != 0){
            firstResult = firstResult * 20 + 1;
        }
        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Document.class);
        jpaQuery.setFirstResult(firstResult);
        jpaQuery.setMaxResults(20);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<Document> results = jpaQuery.getResultList();

        return results;
    }
}
