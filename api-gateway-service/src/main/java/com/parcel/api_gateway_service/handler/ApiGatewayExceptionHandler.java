package com.parcel.api_gateway_service.handler;

import com.parcel.api_gateway_service.dto.ErrorResponseDto;
import com.parcel.api_gateway_service.exception.AuthenticationException;
import com.parcel.api_gateway_service.exception.TokenExpiredException;
import com.parcel.api_gateway_service.exception.TokenNotFoundException;
import io.github.resilience4j.core.lang.NonNullApi;
import io.github.resilience4j.core.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
@NonNullApi
public class ApiGatewayExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return buildResponseEntity(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {
        if (ex instanceof AuthenticationException) {
            return getResponseEntity(ex, HttpStatus.UNAUTHORIZED, request, ex.getMessage());
        } else if (ex instanceof TokenExpiredException) {
            return getResponseEntity(ex, HttpStatus.FORBIDDEN, request, ex.getMessage());
        } else if (ex instanceof TokenNotFoundException) {
            return getResponseEntity(ex, HttpStatus.NOT_FOUND, request, ex.getMessage());
        } else {
            return getResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR, request, ex.getMessage());
        }
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex, HttpStatus status, WebRequest request, String message) {
        log.error("{} : {} : {}", ex.getClass(), ex.getMessage(), ex.getStackTrace());
        var errorResponseDto = ErrorResponseDto.builder().status(status.value()).error(status.getReasonPhrase())
                .message(message).path(((ServletWebRequest) request).getRequest().getRequestURI()).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponseDto, status);
    }
}
