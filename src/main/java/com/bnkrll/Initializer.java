package com.bnkrll;

import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("local")
public class Initializer implements CommandLineRunner {

    private final SessionRepository repository;

    public Initializer(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(Session.builder()
                .sessionId("1")
                .date(LocalDate.now())
                .build());
    }
}
