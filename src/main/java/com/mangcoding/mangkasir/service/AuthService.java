package com.mangcoding.mangkasir.service;

import com.mangcoding.mangkasir.dto.LoginRequest;
import com.mangcoding.mangkasir.dto.LoginResponse;
import com.mangcoding.mangkasir.exception.CustomException;
import com.mangcoding.mangkasir.model.User;
import com.mangcoding.mangkasir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(String username, String gmail, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new CustomException("Password and Confirm Password do not match", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException("Username already exists", HttpStatus.CONFLICT);
        }
        if (userRepository.findByGmail(gmail).isPresent()) {
            throw new CustomException("Gmail already exists", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(username);
        user.setGmail(gmail);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public LoginResponse authenticateUser(LoginRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new CustomException("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new LoginResponse(jwt);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
