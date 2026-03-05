package com.tutor.dto;

import lombok.Data;

@Data
public class SortColumn {
    private String columnName;
    private String direction; // "ASC" or "DESC"
}
