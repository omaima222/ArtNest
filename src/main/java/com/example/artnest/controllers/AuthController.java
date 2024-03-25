package com.example.artnest.controllers;

import com.example.artnest.dtos.AuthDto;
import com.example.artnest.dtos.TokenDto;
import com.example.artnest.dtos.UserDto;
import com.example.artnest.services.Interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth/")
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<TokenDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.signup(userDto));
    }


    @PostMapping("authenticate")
    public ResponseEntity<TokenDto> authenticate(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }
}