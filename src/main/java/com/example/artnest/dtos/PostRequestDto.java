package com.example.artnest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostRequestDto {
    private Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
    @NotBlank
    @NotEmpty
    @NotNull
    private String description;
    @NotBlank
    @NotEmpty
    @NotNull
    private String image;
    @NotNull
    private Long user_id;
}
