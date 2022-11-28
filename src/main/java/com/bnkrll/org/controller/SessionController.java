package com.bnkrll.org.controller;


import com.bnkrll.org.model.Session;
import com.bnkrll.org.service.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@Slf4j
public class SessionController {

    private final SessionRepository sessionRepository;

    public SessionController(@Qualifier("sessionRepositorySimulator")
                                     SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping
    public void saveSession(@RequestBody Session session) {
        sessionRepository.save(session);
    }
}
