package com.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDataModel<T> {
    private List<T> content;
    private Long numberOfRecords;
    private int pageIndex;
    private int pageSize;
}
