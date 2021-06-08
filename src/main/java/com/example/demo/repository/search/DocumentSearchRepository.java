package com.example.demo.repository.search;

import com.example.demo.constant.SortType;
import com.example.demo.dao.Document;
import com.example.demo.dao.entity.SearchEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
// import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional
public class DocumentSearchRepository {

    @Autowired
    ObjectMapper objectMapper;
    
    String[] fields = {"documentTitle", "documentDes"};

    @PersistenceContext
    private EntityManager entityManager;

    FullTextEntityManager fullTextEntityManager;

    @Autowired
    SessionFactory sessionFactory;


    public void indexing() {
        if (fullTextEntityManager == null) return;
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Document> search(String text) throws JsonProcessingException {

        // fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        Session session = sessionFactory.openSession();

        FullTextSession fullTextSession = Search.getFullTextSession(session);

        SearchFactory searchFactory = fullTextSession.getSearchFactory();

        QueryBuilder docBuilder = searchFactory.buildQueryBuilder().forEntity( Document.class ).get();

        

        // indexing();

        // create the query using Hibernate Search query DSL
        // QueryBuilder queryBuilder =
        //         fullTextEntityManager.getSearchFactory()
        //                 .buildQueryBuilder().forEntity(Document.class).get();


        SearchEntity searchEntity = new ObjectMapper().readValue(text, SearchEntity.class);

        Query luceneQuery = docBuilder
    .bool()
      .should( docBuilder.keyword().onFields(fields).matching(searchEntity.getQuery()).createQuery() )
    .createQuery();

    FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Document.class);

        // Query query = queryBuilder
        //         .keyword()
        //         .onFields(fields)
        //         .matching("*" + searchEntity.getQuery().toLowerCase() + "*")
                // .createQuery();

        Integer firstResult = searchEntity.getPage() - 1;
        if(firstResult != 0){
            firstResult = firstResult * 20 + 1;
        }

        Sort sort = null;
        
        if(StringUtils.isNotBlank(searchEntity.getSortField())){
            boolean revert = true;
            if(StringUtils.isNotBlank(searchEntity.getSortType())
                && SortType.ASC.equals(searchEntity.getSortType())){
                    revert = false;
            }
            sort = new Sort(
                new SortField(searchEntity.getSortField(), SortField.Type.INT, revert)
            );
        }

        fullTextQuery.setFirstResult(firstResult).setMaxResults(20);
        if(Objects.nonNull(sort)){
            fullTextQuery.setSort(sort);
        }
            
        

        // wrap Lucene query in an Hibernate Query object
        // FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Document.class);
        // jpaQuery
        //         .setFirstResult(firstResult)
        //         .setMaxResults(20);
        // if(Objects.nonNull(sort)){
        //     jpaQuery.setSort(sort);
        // }

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<Document> results = fullTextQuery.getResultList();

        return results;
    }
}
