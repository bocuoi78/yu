package com.vku.bocuoi.yu.repository;

import com.vku.bocuoi.yu.model.entity.Menu;
import com.vku.bocuoi.yu.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
//    Optional<Menu> findById(Long id);
//
//    List<Menu> findAll();
//
    List<Menu> findAllByRoleListIn(List<Role> roleList);
}
