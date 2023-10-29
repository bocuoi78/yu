package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Documentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentaryRepository extends JpaRepository<Documentary, Long> {
}
