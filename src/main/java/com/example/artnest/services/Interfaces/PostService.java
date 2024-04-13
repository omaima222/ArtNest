package com.example.artnest.services.Interfaces;

import com.example.artnest.dtos.PostRequestDto;
import com.example.artnest.dtos.PostResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface PostService {
    public List<PostResponseDto> getAllPosts();
    public List<PostResponseDto> getAllMyPosts(Long id);
    public PostResponseDto findPostById(Long id) throws EntityNotFoundException;
    public PostResponseDto save(PostRequestDto postDto) throws ValidationException;
    public void delete(Long id);
}
