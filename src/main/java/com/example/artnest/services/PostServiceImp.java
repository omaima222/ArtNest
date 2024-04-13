package com.example.artnest.services;

import com.example.artnest.dtos.PostRequestDto;
import com.example.artnest.dtos.PostResponseDto;
import com.example.artnest.entities.Post;
import com.example.artnest.entities.User;
import com.example.artnest.repositories.PostRepository;
import com.example.artnest.services.Interfaces.PostService;
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
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        return posts.stream().map(p->modelMapper.map(p, PostResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDto> getAllMyPosts(Long id) {
        User artist = modelMapper.map(this.userService.findUserById(id), User.class);
        List<Post> posts = this.postRepository.getAllByUser(artist);
        return posts.stream().map(p->modelMapper.map(p, PostResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostResponseDto findPostById(Long id) throws EntityNotFoundException {
        Optional<Post> post = this.postRepository.findById(id);
        if(!post.isPresent()) throw new EntityNotFoundException("Post not found !");
        return modelMapper.map(post.get(), PostResponseDto.class);
    }

    @Override
    public PostResponseDto save(PostRequestDto postRequestDto) throws ValidationException {
        User user = modelMapper.map(this.userService.findUserById(postRequestDto.getUser_id()), User.class);
        Post post = modelMapper.map(postRequestDto, Post.class);
        post.setUser(user);
        post = this.postRepository.save(post);
        return modelMapper.map(post, PostResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        Post post = modelMapper.map(this.findPostById(id), Post.class);
        this.postRepository.delete(post);
    }
}
