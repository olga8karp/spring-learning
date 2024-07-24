package org.security.authentication.manager;

import lombok.AllArgsConstructor;
import org.security.entity.User;
import org.security.service.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserServiceImpl userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userService.getUser(authentication.getName());
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }
}