package com.parcel.order_service.handler;

import com.parcel.order_service.dto.exception.ErrorResponseDto;
import com.parcel.order_service.exception.BadRequestException;
import com.parcel.order_service.exception.OrderNotFoundException;
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
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

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
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getResponseEntity(ex, status, request, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(Exception ex, WebRequest request) {
        if (ex instanceof BadRequestException) {
            return getResponseEntity(ex, HttpStatus.BAD_REQUEST, request,
                    ex.getMessage());
        } else if (ex instanceof OrderNotFoundException) {
            return getResponseEntity(ex, HttpStatus.NOT_FOUND, request,
                    ex.getMessage());
        } else {
            return getResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR, request,
                    ex.getMessage());
        }
    }

    private ResponseEntity<Object> getResponseEntity(
            Exception ex, HttpStatus status, WebRequest request, String message) {
        log.error("{} : {} : {}", ex.getClass(), ex.getMessage(), ex.getStackTrace());
        var errorResponseDto = ErrorResponseDto.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponseDto, status);
    }
}
