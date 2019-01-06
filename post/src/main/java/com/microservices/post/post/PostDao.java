package com.microservices.post.post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public interface PostDao extends CrudRepository<Post, Integer> {

}