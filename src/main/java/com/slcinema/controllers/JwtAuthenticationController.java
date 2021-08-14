package com.slcinema.controllers;

import com.slcinema.models.AuthenticationRequest;
import com.slcinema.models.AuthenticationResponse;
import com.slcinema.util.JwtTokenUtil;
import com.slcinema.services.CustomUserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    @PostMapping(value = "/login" ,produces = {"application/json"})
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    "Incorrect userName or Password.",
                    HttpStatus.BAD_REQUEST
            );
        }
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authRequest.getUsername());

        //System.out.println(userDetails.isEnabled());
        if(!userDetails.isEnabled()){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Please verify your email");
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public static String getUserFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            return userName;
        }
        return null;
    }

    @GetMapping("/noauth")
    public ResponseEntity<?> noAuth() {
        Map<String, String> body = new HashMap<>();
        body.put("message", "unauthorized");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
