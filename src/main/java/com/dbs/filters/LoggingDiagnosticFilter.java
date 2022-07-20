package com.dbs.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

import static java.lang.String.format;

@Slf4j
@Component
@Order(1)
public class LoggingDiagnosticFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        String requestURI = ((RequestFacade) servletRequest).getRequestURI();

        logMessage(format("[START] Request %s", requestURI));
        filterChain.doFilter(servletRequest, servletResponse);

        Long timeTaken = System.currentTimeMillis() - startTime;
        logMessage(format("[COMPLETE] Request %s in duration of %s ms", requestURI, timeTaken));
    }

    private void logMessage(String message) {
        log.info(message);
    }
}
