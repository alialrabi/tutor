package com.tutor.business.dto;

import lombok.Data;

@Data
public class SortColumn {
    private String columnName;
    private String direction; // "ASC" or "DESC"
}
