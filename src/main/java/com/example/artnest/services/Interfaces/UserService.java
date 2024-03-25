package com.example.artnest.services.Interfaces;

import com.example.artnest.dtos.UserDto;
import com.example.artnest.entities.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();
    public UserDto findUserById(Long id) throws EntityNotFoundException;
    public UserDto findUserByEmail(String email) throws EntityNotFoundException;
    public UserDto save(UserDto userDto) throws ValidationException;
    public void delete(Long id);
}
