package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.DocumentProcessing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentProcessingRepository extends JpaRepository<DocumentProcessing, Long> {
}
