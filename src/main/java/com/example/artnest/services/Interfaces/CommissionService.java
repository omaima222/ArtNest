package com.example.artnest.services.Interfaces;

import com.example.artnest.dtos.CommissionRequestDto;
import com.example.artnest.dtos.CommissionResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface CommissionService {
    public List<CommissionResponseDto> getAllCommissions();
    public List<CommissionResponseDto> getAllByClient(Long client_id);
    public CommissionResponseDto findCommissionById(Long id) throws EntityNotFoundException;
    public CommissionResponseDto save(CommissionRequestDto commissionRequestDto) throws ValidationException;
    public void delete(Long id);
}
