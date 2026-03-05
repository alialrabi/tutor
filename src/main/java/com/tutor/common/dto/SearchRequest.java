package com.tutor.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<FilterColumn> filterList;
    private List<SortColumn> sortCriteria;
    private int page;
    private int size;
}
