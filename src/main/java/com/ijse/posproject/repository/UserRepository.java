package com.ijse.posproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.posproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
