package com.serhiikovtyka.images.controller;

import com.serhiikovtyka.images.dto.ImageMetadataDto;
import com.serhiikovtyka.images.entity.ImageMetadata;
import com.serhiikovtyka.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

  private final ImageService imageService;

  @GetMapping("/images")
  public Page<ImageMetadata> getAllImages(Pageable pageable) {
    return imageService.getAllImages(pageable);
  }

  @GetMapping("/searchImages")
  public Page<ImageMetadataDto> searchImages(@RequestParam String keyword, Pageable pageable) {
    return imageService.searchImagesByClassifier(keyword, pageable);
  }

  @PostMapping(path = "/image/upload")
  public ResponseEntity<String> uploadFile(@RequestParam(value = "images") @NonNull MultipartFile[] multipartFiles) {
    imageService.uploadImage(multipartFiles);
    return ResponseEntity.ok().build();
  }
}