package com.serhiikovtyka.images.exception;


import com.serhiikovtyka.images.entity.enums.ErrorType;

public class UnsupportedExtensionException extends ServiceException {

  private static final String DEFAULT_MESSAGE = "Extension is not supported";

  public UnsupportedExtensionException() {
    super(DEFAULT_MESSAGE);
  }

  public UnsupportedExtensionException(String message) {
    super(message);
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.VALIDATION_ERROR_TYPE;
  }
}
