package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Document;
import com.vku.bocuoi.yu.model.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d FROM Document d WHERE " +
            "(d.isDeleted = false) AND " +
            "(d.organizationIssued.id = :organizationId) AND " +
            "(:keyword IS NULL OR d.name LIKE %:keyword%) AND " +
            "(:documentTypeId IS NULL OR d.type.id = :documentTypeId) AND " +
            "(:fromDate IS NULL OR d.dateIssued >= :fromDate) AND " +
            "(:toDate IS NULL OR d.dateIssued <= :toDate) "
    )
    Page<Document> findDocumentSendByCriteria(
            @Param("organizationId") String organizationId,
            @Param("keyword") String keyword,
            @Param("documentTypeId") Long documentTypeId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            Pageable pageable
    );

    @Query("SELECT d FROM Document d JOIN d.organizationList d_o WHERE " +
            "(d.isDeleted = false) AND " +
            "(d_o.id = :organizationId) AND " +
            "(:keyword IS NULL OR d.name LIKE %:keyword%) AND " +
            "(:documentTypeId IS NULL OR d.type.id = :documentTypeId) AND " +
            "(:fromDate IS NULL OR d.dateIssued >= :fromDate) AND " +
            "(:toDate IS NULL OR d.dateIssued <= :toDate) "
    )
    Page<Document> findDocumentReceiveByCriteria(
            @Param("organizationId") String organizationId,
            @Param("keyword") String keyword,
            @Param("documentTypeId") Long documentTypeId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            Pageable pageable
    );

    @Query("SELECT d FROM Document d WHERE (d.isDeleted = false) AND (d.id = :id)")
    Optional<Document> findById(@Param("id") Long id);

    @Query("SELECT d.organizationList FROM Document d JOIN d.organizationList d_o WHERE" +
            "(d.isDeleted = false) AND " +
            "(d_o.id = :organizationId)"
    )
    Optional<Organization> findOrganizationInDocumentById(@Param("organizationId") Long organizationId);
}
