package com.example.artnest.repositories;

import com.example.artnest.entities.Post;
import com.example.artnest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByUser(User user);
}
