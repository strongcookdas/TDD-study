package org.example.archunittest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {
    public String getHello(){
        return "Hello";
    }
}
