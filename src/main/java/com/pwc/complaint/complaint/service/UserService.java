package com.pwc.complaint.complaint.service;

import com.pwc.complaint.complaint.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);
}
