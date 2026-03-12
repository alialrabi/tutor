package com.tutor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyRoomResponse {
    private String id;
    private String name;
    private String url;
    private String privacy;
}
