package com.regift.regift.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByUser(User user);
    List<Post> findAll();

//    @Override
//    Optional<Post> findById(Long aLong);
}
