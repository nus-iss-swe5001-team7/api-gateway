package com.nus.edu.se.api_gateway.boundary;

import com.nus.edu.se.api_gateway.service.JwtUtil;
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

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody String userName) {
        log.info("Token generated: {}", userName);
        return new ResponseEntity<>(jwtUtil.generateToken(userName), HttpStatus.OK);
    }

}
