package com.serhiikovtyka.images.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serhiikovtyka.images.config.properties.BucketProperties;
import com.serhiikovtyka.images.entity.ImageMetadata;
import com.serhiikovtyka.images.repository.ImageRepository;
import com.serhiikovtyka.images.repository.LabelRepository;
import com.serhiikovtyka.images.service.LabelDetectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ImageServiceImplTest {

  @Mock
  private AmazonS3 s3Client;

  @Mock
  private BucketProperties bucketProperties;

  @Mock
  private LabelDetectionService labelDetectionService;

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private LabelRepository labelRepository;

  private ObjectMapper objectMapper;

  private ImageServiceImpl imageService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    imageService = new ImageServiceImpl(s3Client, bucketProperties, labelDetectionService, imageRepository, labelRepository, objectMapper);
  }

  @Test
  public void testUploadImage() throws IOException {
    MultipartFile multipartFile = createMockMultipartFile("test.jpg");

    when(s3Client.getUrl(eq(bucketProperties.getName()), anyString())).thenReturn(new URL("https://example-bucket.s3.amazonaws.com/test.jpg"));

    when(labelDetectionService.detectLabelsForImage(anyString())).thenReturn(Collections.emptyList());

    imageService.uploadImage(new MultipartFile[]{multipartFile});
  }


  @Test
  public void testGetAllImages() {
    // Arrange
    Pageable pageable = Pageable.unpaged();
    // Mock the imageRepository behavior for retrieving images
    when(imageRepository.findAll(pageable)).thenReturn(Page.empty());

    // Act
    Page<ImageMetadata> result = imageService.getAllImages(pageable);

    // Assert
    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  // Helper method to create a mock MultipartFile for testing
  private MultipartFile createMockMultipartFile(String filename) {
    return new MockMultipartFile("file", filename, "image/jpeg", new byte[0]);
  }
}
