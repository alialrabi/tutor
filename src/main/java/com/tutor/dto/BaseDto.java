package com.tutor.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDto implements Serializable {
    private Long id;
}
