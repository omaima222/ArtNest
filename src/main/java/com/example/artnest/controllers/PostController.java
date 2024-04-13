package com.example.artnest.controllers;

import com.example.artnest.dtos.PostRequestDto;
import com.example.artnest.dtos.PostResponseDto;
import com.example.artnest.services.Interfaces.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/post/")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostResponseDto>> getAll(){
        List<PostResponseDto> posts = this.postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<PostResponseDto>> getAllMyPosts(@PathVariable Long id){
        List<PostResponseDto> posts = this.postService.getAllMyPosts(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id){
        PostResponseDto post = this.postService.findPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> add(@Valid @RequestBody PostRequestDto postRequestDto){
        PostResponseDto post = this.postService.save(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id, @Valid @RequestBody PostRequestDto postRequestDto){
        postRequestDto.setId(id);
        PostResponseDto post = this.postService.save(postRequestDto);
        return ResponseEntity.ok(post);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.postService.delete(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
