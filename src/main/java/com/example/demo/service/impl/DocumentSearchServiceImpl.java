package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.example.demo.dao.Document;
import com.example.demo.dao.entity.SearchEntity;
import com.example.demo.dto.request.DocumentSearchRequest;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.search.DocumentSearchRepository;
import com.example.demo.service.DocumentSearchService;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentSearchServiceImpl implements DocumentSearchService {

    private final DocumentRepository documentRepository;
    private final DocumentSearchRepository documentSearchRepository;

    @Override
    public List<Document> getAll() {

        return documentRepository.findAll();
    }

    @Override
    public List<Document> search(DocumentSearchRequest request) {

        try {
            List<Document> searchList = new ArrayList<>();

            if (Objects.isNull(request)) {
                return searchList;
            }

            String searchEntityAsString = this.convertToString(request);

            searchList = documentSearchRepository.search(searchEntityAsString);

            if (StringUtils.isNotBlank(request.getFilter())) {
                searchList = this.filterList(searchList, request.getFilter());
            }

            return searchList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private List<Document> filterList(List<Document> searchList, String filter) {
        return searchList.stream().filter(o -> filter.equalsIgnoreCase(o.getDocumentSource())).collect(Collectors.toList());
    }

    // public void sortList(List<Document> searchList, DocumentSort sort) {

    //     switch (sort.getSortField()) {
    //         case SortFieldConst.DATE_CREATE:
    //             searchList.sort(new DateCreateCompare());
    //             break;
    //         case SortFieldConst.DOWNLOADED:
    //             searchList.sort(new DownloadedCompare());
    //             break;
    //         case SortFieldConst.REVIEW:
    //             searchList.sort(new ReviewCompare());
    //             break;
    //         default:
    //             break;
    //     }

    //     if (SortType.DESC.equals(sort.getSortType())) {
    //         Collections.reverse(searchList);
    //     }
    // }

    String convertToString(DocumentSearchRequest request) {

        Gson gson = new Gson();

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setPage(request.getPage());
        searchEntity.setQuery(request.getQueryString());

        if(Objects.nonNull(request.getSort())){
            searchEntity.setSortField(request.getSort().getSortField());
            searchEntity.setSortType(request.getSort().getSortType());
        }        

        return gson.toJson(searchEntity);
    }
}
