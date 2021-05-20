package com.example.demo.comparator;

import java.util.Comparator;

import com.example.demo.dao.Document;

public class IdCompare implements Comparator<Document> {

    @Override
    public int compare(Document o1, Document o2) {
        return o1.getId().compareTo(o2.getId());
    }
    
}
