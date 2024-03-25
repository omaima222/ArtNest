package com.example.artnest.dtos;

import com.example.artnest.enums.Progress;
import com.example.artnest.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommissionRequestDto {
    private Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    private String description;
    @NotBlank
    @NotEmpty
    @NotNull
    private Status status;
    @NotBlank
    @NotEmpty
    @NotNull
    private Progress progress;
    @NotNull
    private Long client_id;
    @NotNull
    private Long artist_id;
    @NotNull
    private Long offer_id;
}
