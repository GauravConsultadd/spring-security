package com.project.bookmanagement.Dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDto {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;

}
