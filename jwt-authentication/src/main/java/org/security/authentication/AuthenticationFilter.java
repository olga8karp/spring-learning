package org.security.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.security.authentication.manager.CustomAuthenticationManager;
import org.basicsecurity.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            return customAuthenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Successful authentication");
        String token = JWT.create()
                .withSubject(authResult.getName()) // username
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));
        log.info("token: {}", token);
        response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, SecurityConstants.BEARER_PREFIX + token);
    }

    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("Unsuccessful authentication");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
