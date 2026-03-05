package com.tutor.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDto implements Serializable {
    private Long id;
}
