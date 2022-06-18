package com.enovation.assingmentsolution.controllers;

import com.enovation.assingmentsolution.domains.DeleteResponse;
import com.enovation.assingmentsolution.domains.Post;
import com.enovation.assingmentsolution.domains.PostRequestDto;
import com.enovation.assingmentsolution.domains.PostResponseDto;
import com.enovation.assingmentsolution.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postDto) {

        Post postRequest = modelMapper.map(postDto, Post.class);
        Post post = postService.createPost(postRequest);
        PostResponseDto postResponse = modelMapper.map(post, PostResponseDto.class);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable long id, @RequestBody PostRequestDto postDto) {

        Post postRequest = modelMapper.map(postDto, Post.class);
        Post post = postService.updatePost(id,postRequest);
        PostResponseDto postResponse =  modelMapper.map(post, PostResponseDto.class);

        return ResponseEntity.ok().body(postResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable(name = "id") Long id) {

        Post post = postService.getPostById(id);
        PostResponseDto postResponse =  modelMapper.map(post, PostResponseDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> deletePost(@PathVariable(name = "id") Long id) {

        postService.deletePost(id);
        DeleteResponse apiResponse = new DeleteResponse(Boolean.TRUE, "Post deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {

        return postService.getAllPosts().stream().map(post ->modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());
    }

}
