package com.example.demo.repository.search;

import com.example.demo.dao.Document;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class DocumentSearchRepository {
    
    String[] fields = {"documentTitle", "documentDes", "documentSource"};

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

    public List<Document> search(String text) {

        fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        // indexing();

        // create the query using Hibernate Search query DSL
        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Document.class).get();


        Query query = queryBuilder
                .keyword()
                .onFields(fields)
                .matching("*" + text.toLowerCase() + "*")
                .createQuery();

        // wrap Lucene query in an Hibernate Query object
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Document.class);

        // execute search and return results (sorted by relevance as default)
        @SuppressWarnings("unchecked")
        List<Document> results = jpaQuery.getResultList();

        return results;
    }
}
