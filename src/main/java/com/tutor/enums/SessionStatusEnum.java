package com.tutor.enums;

import lombok.Getter;

@Getter
public enum SessionStatusEnum {

    RESERVED(0),
    DONE(1),
    CANCELED(2);

    private Integer value;

    SessionStatusEnum(Integer value) {
        this.value=value;
    }

}
