package com.tutor.business.dto;

import lombok.Data;

@Data
public class FilterColumn {
    private String columnName;
    private String operation; // e.g., "EQUAL", "LIKE", "GT", "LT"
    private String value;
}
