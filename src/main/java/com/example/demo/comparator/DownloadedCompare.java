package com.example.demo.comparator;

import java.util.Comparator;

import com.example.demo.dao.Document;

public class DownloadedCompare implements Comparator<Document>{

    @Override
    public int compare(Document o1, Document o2) {
        return o1.getDownloaded().compareTo(o2.getDownloaded());
    }
    
}
