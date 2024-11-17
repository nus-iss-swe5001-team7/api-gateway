package com.nus.edu.se.api_gateway.boundary;

import com.nus.edu.se.api_gateway.service.JwtUtil;
import com.nus.edu.se.api_gateway.service.TokenBlacklistService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("jwt")
public class JwtTokenController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TokenBlacklistService tokenBlacklistService;

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody String userName) {
        log.info("Token generated: {}", userName);
        return new ResponseEntity<>(jwtUtil.generateToken(userName), HttpStatus.OK);
    }

    @PostMapping("/blacklistToken")
    public ResponseEntity<String> blacklistToken(@RequestBody String token) {
        tokenBlacklistService.blacklistToken(token);
        log.info("Token blacklisted: {}", token);
        tokenBlacklistService.blacklistToken(token);
        return new ResponseEntity<>("Token has been blacklisted.", HttpStatus.OK);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        log.info("Token validated: {}", token);
        return new ResponseEntity<>(jwtUtil.validateToken(token), HttpStatus.OK);
    }
}
