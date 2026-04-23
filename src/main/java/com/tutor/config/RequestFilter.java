package com.tutor.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class RequestFilter implements Filter {

    private static final String REQUEST_UUID_KEY = "requestUUID";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String requestUUID = UUID.randomUUID().toString();
            MDC.put(REQUEST_UUID_KEY, requestUUID);
            log.info("requestUUID {}", requestUUID);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(REQUEST_UUID_KEY);
        }
    }
}
