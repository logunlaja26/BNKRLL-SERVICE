package com.bnkrll.controller;

import com.bnkrll.exceptions.SessionNotFoundException;
import com.bnkrll.model.Session;
import com.bnkrll.model.UpdateBuyInRequest;
import com.bnkrll.service.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/session")
@Slf4j
@CrossOrigin(origins = {"http://localhost:5173","https://friendly-genie-9b6cb8.netlify.app"})
public class SessionController {

    private final SessionRepository sessionRepository;


    public SessionController(@Qualifier("inMemorySessionRepository")
                                     SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/save")
    public void saveSession(@RequestBody Session session) {
        sessionRepository.save(session);
    }


    @GetMapping("/{numOfSession}")
    public List<Session> getPreviousSession(
            @PathVariable int numOfSession) {
        return sessionRepository.getLastSessions(numOfSession);
    }

    @PatchMapping("/buy-in")
    public void updateBuyInOfSession(@RequestBody UpdateBuyInRequest updatedSession
    ) {
        Session currentSession = sessionRepository.findById(updatedSession.getSessionId())
                .orElseThrow(() -> new SessionNotFoundException("Session can't be located in the database - " + updatedSession.getSessionId()));
        currentSession.setBuyin(updatedSession.getBuyin());
        sessionRepository.save(currentSession);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCurrentSession(@PathVariable String id) {
        sessionRepository.deleteById(id);
    }


}
