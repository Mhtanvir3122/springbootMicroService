package com.example.AuthService.repository;

import com.example.AuthService.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentIsNull(); // root menu

    List<Menu> findByIdIn(List<Long> ids);

}
