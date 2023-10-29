package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.StudentUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentUnitRepository extends JpaRepository<StudentUnit, Long> {
}
