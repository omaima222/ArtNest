package com.example.artnest.services;

import com.example.artnest.config.JwtService;
import com.example.artnest.dtos.UserDto;
import com.example.artnest.entities.User;
import com.example.artnest.repositories.UserRepository;
import com.example.artnest.services.Interfaces.UserService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;


    @Test
    public void testSignup_NonUniqueEmail() {
        UserDto userDto = new UserDto();
        userDto.setEmail("bagel@gmail.com");
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(ValidationException.class, () -> userService.signup(userDto));
        verify(userRepository).findByEmail(userDto.getEmail());
    }

}