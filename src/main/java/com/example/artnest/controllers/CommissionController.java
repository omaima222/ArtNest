package com.example.artnest.controllers;


import com.example.artnest.dtos.CommissionRequestDto;
import com.example.artnest.dtos.CommissionResponseDto;
import com.example.artnest.services.Interfaces.CommissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/commission/")
public class CommissionController {
    private final CommissionService commissionService;

    @GetMapping("")
    public ResponseEntity<List<CommissionResponseDto>> getAll(){
        List<CommissionResponseDto> offers = this.commissionService.getAllCommissions();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<List<CommissionResponseDto>> getAllByClient(@PathVariable Long id){
        List<CommissionResponseDto> offers = this.commissionService.getAllByClient(id);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommissionResponseDto> findById(@PathVariable Long id){
        CommissionResponseDto offer = this.commissionService.findCommissionById(id);
        return ResponseEntity.ok(offer);
    }

    @PostMapping("")
    public ResponseEntity<CommissionResponseDto> add(@Valid @RequestBody CommissionRequestDto commissionRequestDto){
        CommissionResponseDto offer = this.commissionService.save(commissionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommissionResponseDto> update(@PathVariable Long id, @Valid @RequestBody CommissionRequestDto commissionRequestDto){
        commissionRequestDto.setId(id);
        CommissionResponseDto offer = this.commissionService.save(commissionRequestDto);
        return ResponseEntity.ok(offer);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.commissionService.delete(id);
        return ResponseEntity.ok("Commission deleted successfully");
    }
}
