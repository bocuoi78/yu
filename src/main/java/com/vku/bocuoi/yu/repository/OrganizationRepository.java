package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT o FROM Organization o WHERE (o.isDeleted = false ) AND (o.organizationId = :organizationId)")
    List<Organization> findAllOrganizationChildById(@Param("organizationId") Long organizationId);
}
