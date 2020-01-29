package com.regift.regift.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);
    Optional<User> findById(Long Id);
}

