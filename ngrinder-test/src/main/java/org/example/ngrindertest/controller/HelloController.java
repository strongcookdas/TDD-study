package org.example.ngrindertest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        String string= "HelloWorld";
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
}
