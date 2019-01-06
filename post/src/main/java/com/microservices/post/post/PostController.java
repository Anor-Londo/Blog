package com.microservices.post.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PostController {

    private PostDao postDao;
    private PostService postService;

    @Autowired
    private Environment environment;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public PostService getPostService() {
        return postService;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }


    @RequestMapping(value = "post", method = RequestMethod.GET)
    public Iterable<Post> retrieveAllPosts(){
        return postDao.findAll();
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Optional<Post> retrieveOnePost(@PathVariable int id){
        return postDao.findById(id);
    }

    @RequestMapping(value = "/post/words/{string}", method = RequestMethod.GET)
    public List<Post> retrieveFoundPosts(@PathVariable String string) {
        List<Post> posts = postService.findByCharSequance(string);
        return posts;
    }

    @RequestMapping(value = "/post/createPost",method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Post createPost(@RequestBody Post request){

        Post post = postService.createPost(request.getTitle(), request.getDescription());
        post.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return post;
    }
}