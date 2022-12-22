package com.bnkrll.controller;

import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping
    public List<Session> getPreviousSessions(
            @RequestParam(value = "last", defaultValue = "30")
                    int last) {
        List<Session> previousSessions = sessionRepository.getLastSessions(last);
        return previousSessions;
    }
}
