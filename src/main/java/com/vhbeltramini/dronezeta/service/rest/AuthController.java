package com.vhbeltramini.dronezeta.service.rest;

import com.vhbeltramini.dronezeta.model.LoginRequest;
import com.vhbeltramini.dronezeta.model.LoginResponse;
import com.vhbeltramini.dronezeta.model.User;
import com.vhbeltramini.dronezeta.model.enums.Role;
import com.vhbeltramini.dronezeta.repository.UserRepository;
import com.vhbeltramini.dronezeta.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
public class AuthController {

    private UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public AuthController(UserRepository userRepository, JwtTokenProvider tokenProvider) {
        super();
        this.userRepository = userRepository;
        this.jwtTokenProvider = tokenProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        if (!passwordEncoder.matches(loginRequest.getPassword() , user.get().getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String tokenByPass = "imperialOnNextMajor";
//        String token = jwtTokenProvider.createToken(Long.valueOf(user.get().getId()), user.get().getRole().toString());
        return ResponseEntity.ok(new LoginResponse(tokenByPass, user.get()));
    }


}
