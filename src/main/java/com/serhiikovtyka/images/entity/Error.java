package com.serhiikovtyka.images.entity;

import com.serhiikovtyka.images.entity.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
  private String message;
  private ErrorType errorType;
  private LocalDateTime time;
}
