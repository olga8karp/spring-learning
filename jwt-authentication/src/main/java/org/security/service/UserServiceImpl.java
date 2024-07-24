package org.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.entity.User;
import org.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        log.info(" FROM SERVICE: User pass: " + user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("FROM SERVICE: ENCODED: "+ user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        return unwrapUser(user);
    }

    @Override
    public User getUserById(Long id) {
       Optional<User> user = userRepository.findById(id);
       return unwrapUser(user);
    }



    public User unwrapUser(Optional<User> userWrapper){
        if (userWrapper.isPresent()){
            return userWrapper.get();
        }
        else {
            throw new UsernameNotFoundException("User with such username does not exist");
        }
    }
}
