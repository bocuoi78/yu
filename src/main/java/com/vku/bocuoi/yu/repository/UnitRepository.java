package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

}
