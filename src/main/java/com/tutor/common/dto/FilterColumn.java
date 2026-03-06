package com.tutor.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterColumn {
    private String columnName;
    private String operation; // e.g., "EQUAL", "LIKE", "GT", "LT"
    private String value;
}
