package com.bnkrll.org.controller;


import com.bnkrll.org.model.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/session")
@Slf4j
public class SessionController {

    @GetMapping("/{sessionId}")
    public Session getSession(@PathVariable String sessionId){
        String id = UUID.randomUUID().toString();
        Session response = new Session(id);
        log.info("The session id is: {}", response.getSessionId());
        return response;
    }
}
