package com.example.ServiceClassRoutine.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaModel {
    private Integer page;
    private Integer prevPage;
    private Integer nextPage;
    private Integer limit;
    private Long totalRecords;
    private Integer resultCount;
    private Integer totalPageCount;
    private List< SortModel> sort;
}