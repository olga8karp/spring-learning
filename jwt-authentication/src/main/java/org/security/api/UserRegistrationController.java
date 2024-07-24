package org.security.api;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.entity.User;
import org.security.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        log.info("Request to register under path: /register");
        log.info("User info: " + user.getUsername() + " , " + user.getPassword());
        return ResponseEntity.ok(userService.saveUser(user));
    }


}
