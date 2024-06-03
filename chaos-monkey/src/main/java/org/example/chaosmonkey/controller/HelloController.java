package org.example.chaosmonkey.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/test")
    public ResponseEntity<String> getMessage() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
