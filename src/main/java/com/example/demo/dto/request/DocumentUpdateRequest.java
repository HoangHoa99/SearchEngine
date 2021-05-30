package com.example.demo.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentUpdateRequest {
    
    @NotNull
    private Long id;
}
