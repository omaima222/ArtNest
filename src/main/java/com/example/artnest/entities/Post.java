package com.example.artnest.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
