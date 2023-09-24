package com.serhiikovtyka.images.repository;

import com.serhiikovtyka.images.entity.ImageLabels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LabelRepository extends JpaRepository<ImageLabels, UUID> {
  Optional<List<ImageLabels>> findAllByLabel(String label);
}
