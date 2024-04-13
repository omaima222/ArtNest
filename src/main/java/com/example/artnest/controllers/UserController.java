package com.example.artnest.controllers;

import com.example.artnest.dtos.UserDto;
import com.example.artnest.services.Interfaces.UserService;
import jakarta.validation.Valid;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user/")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> users = this.userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto user = this.userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        userDto.setId(id);
        UserDto user = this.userService.save(userDto);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
