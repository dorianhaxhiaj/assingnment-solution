package com.enovation.assingmentsolution.services;

import com.enovation.assingmentsolution.domains.Post;
import com.enovation.assingmentsolution.exceptions.ResourceNotFoundException;
import com.enovation.assingmentsolution.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, Post newPost) {
        Post oldPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        oldPost.setContent(newPost.getContent());
        oldPost.setTitle(newPost.getTitle());
        oldPost.setCreationDate(newPost.getCreationDate());
        return postRepository.save(oldPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public Post getPostById(long id) {
        return  postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    }

}
