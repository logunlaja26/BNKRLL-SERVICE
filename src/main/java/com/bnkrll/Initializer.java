package com.bnkrll;

import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Component
@Profile("local")
public class Initializer implements CommandLineRunner {

    private final SessionRepository repository;

    public Initializer(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        LocalDateTime now = LocalDateTime.now();
        repository.save(Session.builder()
                .sessionId("1")
                .date(now.minusDays(2L))
                .build());

        repository.save(Session.builder()
                .sessionId("2")
                .date(now.minusDays(1L))
                .build());

        repository.save(Session.builder()
                .sessionId("3")
                .date(now)
                .build());
        log.info("saved sessions into the db");
    }
}
