package com.ijse.posproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.posproject.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
