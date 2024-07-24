package org.basicsecurity.api;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.basicsecurity.service.UserService;
import org.basicsecurity.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestParam("fullName") String name,
        @RequestParam("email") String email,
        @RequestParam("username") String username,
        @RequestParam("password") String password) {

        log.info("Request to register under path: /register");
        return ResponseEntity.ok(userService.createUser(username, password, name, email));
    }

    @GetMapping("/getById")
    public ResponseEntity<User> getUserById(@RequestParam("id") int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(
        @RequestParam("username") String username,
        @RequestParam("password") String password) {
        log.info("Request received for /users/login");
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
        log.info("Authorities: " + authentication.getAuthorities());
        log.info("Details: " + authentication.getDetails());
        log.info("Credentials: " + authentication.getCredentials());
        log.info("Principal: " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(("User '" + userDetails.getUsername() + "' logged in successfully!"));
    }
}
