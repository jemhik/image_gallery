package com.serhiikovtyka.images.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import com.serhiikovtyka.images.config.properties.BucketProperties;
import com.serhiikovtyka.images.service.LabelDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelDetectionServiceImpl implements LabelDetectionService {

  private final BucketProperties bucketProperties;

  @Override
  public List<Label> detectLabelsForImage(String fileName) {
    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

    DetectLabelsRequest request = new DetectLabelsRequest()
            .withImage(new Image().withS3Object(new S3Object().withName(fileName).withBucket(bucketProperties.getName())))
            .withMaxLabels(10).withMinConfidence(75F);

    DetectLabelsResult result = rekognitionClient.detectLabels(request);

    return result.getLabels();
  }
}