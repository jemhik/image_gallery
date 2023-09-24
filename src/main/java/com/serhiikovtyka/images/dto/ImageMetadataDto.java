package com.serhiikovtyka.images.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ImageMetadataDto {
  private UUID id;
  private String url;
}
