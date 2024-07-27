package hhplus.ecommerce.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        long timeTaken = System.currentTimeMillis() - startTime;

        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        String requestParameters = getRequestParameters(request);

        int status = response.getStatus();
        if (status >= 500) {
            log.error("Filter Logs: METHOD = {}; REQUEST URI = {}; REQUEST PARAMETERS = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}; TIME TAKEN = {} ms",
                    request.getMethod(), request.getRequestURI(), requestParameters, requestBody, status, responseBody, timeTaken);
        } else if (status >= 400) {
            log.warn("Filter Logs: METHOD = {}; REQUEST URI = {}; REQUEST PARAMETERS = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}; TIME TAKEN = {} ms",
                    request.getMethod(), request.getRequestURI(), requestParameters, requestBody, status, responseBody, timeTaken);
        } else {
            log.info("Filter Logs: METHOD = {}; REQUEST URI = {}; REQUEST PARAMETERS = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}; TIME TAKEN = {} ms",
                    request.getMethod(), request.getRequestURI(), requestParameters, requestBody, status, responseBody, timeTaken);
        }


        contentCachingResponseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
        } catch (IOException e) {
            log.error("Error while converting byte array to string", e);
            return "";
        }
    }

    private String getRequestParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder parameters = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            parameters.append(entry.getKey()).append("=").append(String.join(",", entry.getValue())).append(" ");
        }
        return parameters.toString().trim();
    }
}