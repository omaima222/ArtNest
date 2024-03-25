package com.example.artnest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OfferRequestDto {
    private Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
    @NotBlank
    @NotEmpty
    @NotNull
    private String image;
    @NotNull
    private Float price;
    @NotNull
    private Long user_id;
}
