package com.example.demo.dto.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MasterDataSearchResponse {
 
    List<String> docSorts;
    List<String> docSources;
}
