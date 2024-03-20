package com.project.bookmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookmanagement.Dtos.AuthRequestDto;
import com.project.bookmanagement.Dtos.JwtResponseDTO;
import com.project.bookmanagement.services.JwtService;


@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            String accessToken = jwtService.GenerateToken(authRequestDTO.getUsername());
            JwtResponseDTO responseDTO = JwtResponseDTO.builder().accessToken(accessToken).build();
            return ResponseEntity.ok(responseDTO);
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/api/v1/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    } 

    @GetMapping("/api/v1/getUserByToken")
    public ResponseEntity<UserDetails> getUserByToken() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    
}
