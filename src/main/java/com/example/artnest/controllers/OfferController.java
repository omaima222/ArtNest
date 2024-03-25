package com.example.artnest.controllers;


import com.example.artnest.dtos.OfferRequestDto;
import com.example.artnest.dtos.OfferResponseDto;
import com.example.artnest.services.Interfaces.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/offer/")
public class OfferController {
    private final OfferService offerService;

    @GetMapping("")
    public ResponseEntity<List<OfferResponseDto>> getAll(){
        List<OfferResponseDto> offers = this.offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("{id}")
    public ResponseEntity<OfferResponseDto> findById(@PathVariable Long id){
        OfferResponseDto offer = this.offerService.findOfferById(id);
        return ResponseEntity.ok(offer);
    }

    @PostMapping("")
    public ResponseEntity<OfferResponseDto> add(@Valid @RequestBody OfferRequestDto offerRequestDto){
        OfferResponseDto offer = this.offerService.save(offerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @PutMapping("{id}")
    public ResponseEntity<OfferResponseDto> update(@PathVariable Long id, @Valid @RequestBody OfferRequestDto offerRequestDto){
        offerRequestDto.setId(id);
        OfferResponseDto offer = this.offerService.save(offerRequestDto);
        return ResponseEntity.ok(offer);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.offerService.delete(id);
        return ResponseEntity.ok("Offer deleted successfully");
    }
}
