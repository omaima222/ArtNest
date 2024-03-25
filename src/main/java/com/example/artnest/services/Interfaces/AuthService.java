package com.example.artnest.services.Interfaces;

import com.example.artnest.dtos.AuthDto;
import com.example.artnest.dtos.TokenDto;
import com.example.artnest.dtos.UserDto;

public interface AuthService {
    public TokenDto signup(UserDto userDto) throws jakarta.validation.ValidationException;
    public TokenDto login(AuthDto authDto);
}
