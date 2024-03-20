package com.project.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookmanagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public User findByUsername(String username);
}
