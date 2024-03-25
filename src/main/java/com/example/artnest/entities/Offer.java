package com.example.artnest.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "offer")
    private List<Commission> commissions;
}
