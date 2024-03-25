package com.example.artnest.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthDto {
    @NotNull
    @NotEmpty
    @NotBlank
    public String email;

    @NotNull
    @NotEmpty
    @NotBlank
    public String password;
}
