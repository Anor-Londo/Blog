package com.microservices.post.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }


    @Override
    public Post createPost(String title, String description) {
        Post post = new Post(title, description);
        postDao.save(post);
        return null;
    }

    @Override
    public List<Post> findByCharSequance(String cs) {
        List<Post> postsFound = new ArrayList<Post>();
        Iterable<Post> allPosts = postDao.findAll();
        for (Post post: allPosts) {
            if (post.getTitle().contains(cs) || post.getDescription().contains(cs)){
                postsFound.add(post);
            }
        }
        return postsFound;
    }
}
