package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByResetPasswordCode(String token);
//    Optional<Student>  findStudentBysId(String sId);
}
