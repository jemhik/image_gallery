package com.serhiikovtyka.images.controller;

import com.serhiikovtyka.images.entity.Error;
import com.serhiikovtyka.images.entity.enums.ErrorType;
import com.serhiikovtyka.images.exception.ServiceException;
import com.serhiikovtyka.images.exception.UnsupportedExtensionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(UnsupportedExtensionException.class)
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public Error handleUnsupportedExtensionException(UnsupportedExtensionException ex, HandlerMethod hm) {
    log.error("handleException: message: {}, method: {}", ex.getMessage(), hm.getMethod().getName(), ex);
    return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("handleMethodArgumentNotValidException: message: {}", ex.getMessage(), ex);
    return ex.getBindingResult().getAllErrors().stream()
            .map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
            .collect(Collectors.toList());
  }

  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleServiceException(ServiceException ex, HandlerMethod hm) {
    log.error("handleServiceException: message:{}, method: {}", ex.getMessage(), hm.getMethod().getName(), ex);
    return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Error handleException(Exception ex, HandlerMethod hm) {
    log.error("handleException: message: {}, method: {}", ex.getMessage(), hm.getMethod().getName(), ex);
    return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
  }
}
