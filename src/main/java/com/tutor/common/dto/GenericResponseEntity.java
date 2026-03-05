package com.tutor.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseEntity<T> {
    private String responseStatus;
    private String requestUUID;
    private T data;
    private String traceError;

    private static final String REQUEST_UUID_KEY = "requestUUID";

    public static <T> GenericResponseEntity<T> success(T data) {
        return GenericResponseEntity.<T>builder()
                .responseStatus("SUCCESS")
                .requestUUID(MDC.get(REQUEST_UUID_KEY))
                .data(data)
                .build();
    }

    public static <T> GenericResponseEntity<T> error(String traceError) {
        return GenericResponseEntity.<T>builder()
                .responseStatus("ERROR")
                .requestUUID(MDC.get(REQUEST_UUID_KEY))
                .traceError(traceError)
                .build();
    }
}
