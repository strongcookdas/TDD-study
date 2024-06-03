package org.example.archunittest.controller;

import lombok.RequiredArgsConstructor;
import org.example.archunittest.service.HelloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final  HelloService helloService;
    public ResponseEntity<String> getHello(){
        String message = helloService.getHello();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
