package com.example.artnest.services;

import com.example.artnest.dtos.OfferRequestDto;
import com.example.artnest.dtos.OfferResponseDto;
import com.example.artnest.entities.Offer;
import com.example.artnest.entities.User;
import com.example.artnest.repositories.OfferRepository;
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
public class OfferServiceImp implements OfferService {

    private final OfferRepository offerRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;


    @Override
    public List<OfferResponseDto> getAllOffers() {
        List<Offer> offers = this.offerRepository.findAll();
        return offers.stream().map(o->modelMapper.map(o, OfferResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public OfferResponseDto findOfferById(Long id) throws EntityNotFoundException {
        Optional<Offer> offer = this.offerRepository.findById(id);
        if(!offer.isPresent()) throw new EntityNotFoundException("Offer not found !");
        return modelMapper.map(offer.get(), OfferResponseDto.class);
    }

    @Override
    public OfferResponseDto save(OfferRequestDto offerRequestDto) throws ValidationException {
        User user = modelMapper.map(this.userService.findUserById(offerRequestDto.getUser_id()), User.class);
        Offer offer = modelMapper.map(offerRequestDto, Offer.class);
        offer.setUser(user);
        offer = this.offerRepository.save(offer);
        return modelMapper.map(offer, OfferResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        Offer offer = modelMapper.map(this.findOfferById(id), Offer.class);
        this.offerRepository.delete(offer);
    }
}
