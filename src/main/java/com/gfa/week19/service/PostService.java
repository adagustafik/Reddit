package com.gfa.week19.service;

import com.gfa.week19.model.Post;
import org.springframework.data.domain.Page;

public interface PostService {

    Page<Post> findAll(int page, int size);
    Page<Post> getLoggedInUserPosts(int page, int size);
    void votePost(Long id, Boolean up);
    void addPost(Post post);
}