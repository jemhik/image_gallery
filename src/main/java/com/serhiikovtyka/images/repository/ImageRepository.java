package com.serhiikovtyka.images.repository;

import com.serhiikovtyka.images.entity.ImageMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageMetadata, UUID> {
  Page<ImageMetadata> findAllByIdIn(List<UUID> imageIds, Pageable pageable);
}
