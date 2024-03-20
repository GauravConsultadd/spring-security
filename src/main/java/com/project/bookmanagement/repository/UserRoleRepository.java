package com.project.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookmanagement.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    public UserRole findByName(String name);
}
