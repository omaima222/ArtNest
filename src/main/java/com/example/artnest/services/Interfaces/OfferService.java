package com.example.artnest.services.Interfaces;

import com.example.artnest.dtos.OfferRequestDto;
import com.example.artnest.dtos.OfferResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import java.util.List;

public interface OfferService {
    public List<OfferResponseDto> getAllOffers();
    public OfferResponseDto findOfferById(Long id) throws EntityNotFoundException;
    public OfferResponseDto save(OfferRequestDto offerRequestDto) throws ValidationException;
    public void delete(Long id);
}
