package org.basicsecurity.service;

import lombok.AllArgsConstructor;
import org.basicsecurity.repository.UserRepository;
import org.basicsecurity.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    public User createUser(String username, String password, String fullName, String email) {
        return userRepository.save(new User(username, encoder.encode(password), fullName, email));
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

}
