package com.example.artnest.services;

import com.example.artnest.dtos.CommissionRequestDto;
import com.example.artnest.dtos.CommissionResponseDto;
import com.example.artnest.entities.Commission;
import com.example.artnest.entities.Offer;
import com.example.artnest.entities.User;
import com.example.artnest.repositories.CommissionRepository;
import com.example.artnest.services.Interfaces.CommissionService;
import com.example.artnest.services.Interfaces.OfferService;
import com.example.artnest.services.Interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommissionServiceImp implements CommissionService {
    private final CommissionRepository commissionRepository;
    private final UserService userService;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Override
    public List<CommissionResponseDto> getAllCommissions() {
        List<Commission> commissions = this.commissionRepository.findAll();
        return commissions.stream().map(c->modelMapper.map(c, CommissionResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommissionResponseDto> getAllByClient(Long client_id) {
        User client = modelMapper.map(this.userService.findUserById(client_id), User.class);
        List<Commission> commissions = this.commissionRepository.getAllByClient(client);
        return commissions.stream().map(c->modelMapper.map(c, CommissionResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public CommissionResponseDto findCommissionById(Long id) throws EntityNotFoundException {
        Optional<Commission> commission = this.commissionRepository.findById(id);
        if(!commission.isPresent()) throw new EntityNotFoundException("Commission not found !");
        return modelMapper.map(commission.get(), CommissionResponseDto.class);
    }

    @Override
    public CommissionResponseDto save(CommissionRequestDto commissionRequestDto) throws ValidationException {
        User client = modelMapper.map(this.userService.findUserById(commissionRequestDto.getClient_id()), User.class);
        User artist = modelMapper.map(this.userService.findUserById(commissionRequestDto.getArtist_id()), User.class);
        Offer offer = modelMapper.map(this.offerService.findOfferById(commissionRequestDto.getOffer_id()), Offer.class);
        Commission commission = modelMapper.map(commissionRequestDto, Commission.class);
        commission.setClient(client);
        commission.setArtist(artist);
        commission.setOffer(offer);
        commission = this.commissionRepository.save(commission);
        return modelMapper.map(commission, CommissionResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        Commission commission = modelMapper.map(this.findCommissionById(id), Commission.class);
        this.commissionRepository.delete(commission);
    }
}
