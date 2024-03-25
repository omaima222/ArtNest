package com.example.artnest.entities;

import com.example.artnest.enums.Progress;
import com.example.artnest.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Status status;
    private Progress progress;

    @ManyToOne()
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne()
    @JoinColumn(name = "artist_id")
    private User artist;

    @ManyToOne()
    @JoinColumn(name = "offer_id")
    private Offer offer;

}
