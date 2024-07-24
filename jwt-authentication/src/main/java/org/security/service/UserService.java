package org.security.service;


import org.security.entity.User;

public interface UserService {
    User saveUser(User user);
    User getUser(String username);
    User getUserById(Long id);
}
