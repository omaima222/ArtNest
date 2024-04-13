package com.example.artnest.services;

import com.example.artnest.config.JwtService;
import com.example.artnest.dtos.AuthDto;
import com.example.artnest.dtos.TokenDto;
import com.example.artnest.dtos.UserDto;
import com.example.artnest.entities.User;
import com.example.artnest.repositories.UserRepository;
import com.example.artnest.services.Interfaces.AuthService;
import com.example.artnest.services.Interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(u->modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    public UserDto findUserById(Long id) throws EntityNotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()) throw new EntityNotFoundException("User not found !");
        return modelMapper.map(user.get(), UserDto.class);
    }

    public UserDto findUserByEmail(String email) throws EntityNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if(!user.isPresent()) throw new EntityNotFoundException("User not found !");
        return modelMapper.map(user.get(), UserDto.class);
    }

    public UserDto save(UserDto userDto) throws ValidationException {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        if(userDto.getId()!=null) user.setId(userDto.getId());
        return modelMapper.map(this.userRepository.save(user), UserDto.class);
    }

    public TokenDto signup(UserDto userDto) throws jakarta.validation.ValidationException {
        Optional<User> existingUser = this.userRepository.findByEmail(userDto.getEmail());
        if(existingUser.isPresent()) throw new ValidationException("This Email is already taken !");
        UserDto savedUser = this.save(userDto);
        User user = modelMapper.map(savedUser, User.class);
        String jwt = this.jwtService.generateToken(user);
        return new TokenDto(jwt, user.getId(), user.getRole().name());
    }

    public TokenDto login(AuthDto authDto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword())
        );
        User user = modelMapper.map(this.findUserByEmail(authDto.getEmail()), User.class);
        String token = jwtService.generateToken(user);
        return new TokenDto(token, user.getId(), user.getRole().name());
    }

    public void delete(Long id) {
        User user = modelMapper.map(this.findUserById(id), User.class);
        this.userRepository.delete(user);
    }
}
