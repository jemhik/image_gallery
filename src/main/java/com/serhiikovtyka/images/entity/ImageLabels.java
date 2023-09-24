package com.serhiikovtyka.images.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageLabels {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String label;
  @ManyToOne
  @JoinColumn(name = "image_id", nullable = false)
  private ImageMetadata imageMetadata;
}
