package com.linx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linx.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}