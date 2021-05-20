package com.example.demo.comparator;

import java.util.Comparator;

import com.example.demo.dao.Document;

public class ReviewCompare implements Comparator<Document> {

    @Override
    public int compare(Document o1, Document o2) {
        return o1.getReview().compareTo(o2.getReview());
    }
    
}
