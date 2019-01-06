package com.microservices.post.post;

import java.util.List;

public interface PostService {

    Post createPost(String title, String description);
    List<Post> findByCharSequance(String cs);
}
