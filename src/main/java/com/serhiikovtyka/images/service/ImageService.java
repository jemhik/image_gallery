package com.serhiikovtyka.images.service;

import com.serhiikovtyka.images.dto.ImageMetadataDto;
import com.serhiikovtyka.images.entity.ImageMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
  Page<ImageMetadata> getAllImages(Pageable pageable);

  Page<ImageMetadataDto> searchImagesByClassifier(String keyword, Pageable pageable);

  void uploadImage(MultipartFile[] multipartFiles);
}
