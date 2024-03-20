package com.project.bookmanagement.services;

import com.project.bookmanagement.Dtos.RegistrationDto;
import com.project.bookmanagement.entity.User;

public interface UserService {
    public abstract void createUser(RegistrationDto user) throws Exception;
    public abstract User getUserById(Long id) throws Exception;
}
