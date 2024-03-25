package com.example.artnest.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class TokenDto {
    @NotNull
    @NotEmpty
    @NotBlank
    String token;
}
