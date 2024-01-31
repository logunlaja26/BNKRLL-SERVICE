package com.bnkrll.features;

import com.bnkrll.model.Session;
import com.bnkrll.service.InMemorySessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SessionRepositoryTest {

    @Autowired
    private InMemorySessionRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void getSessionsFromDatabase() {
        LocalDateTime now = LocalDateTime.now();
        Session sessionOne = Session.builder()
                .sessionId("1")
                .date(now.minusDays(1L))
                .build();
        Session sessionTwo = Session.builder()
                .sessionId("2")
                .date(now)
                .build();
        Session sessionThree = Session.builder()
                .sessionId("3")
                .date(now.minusDays(2L))
                .build();
        repository.save(sessionOne);
        repository.save(sessionTwo);
        repository.save(sessionThree);

        List<Session> lastSessions = repository
                .getLastSessions(2);

        assertEquals(2, lastSessions.size());
        assertEquals(sessionTwo, lastSessions.get(0));
        assertEquals(sessionOne, lastSessions.get(1));
    }

    @Test
    void returnsAllSessionsWhenNumberOfSessionsRequestedIsGreaterThanSavedSessions() {
        Session sessionOne = Session.builder()
                .sessionId("1")
                .date(LocalDateTime.now())
                .build();
        repository.save(sessionOne);

        List<Session> lastSessions = repository
                .getLastSessions(2);

        assertEquals(1, lastSessions.size());
        assertEquals(sessionOne, lastSessions.get(0));
    }

    @Test
    void canDeleteBySessionId(){
        Session sessionOne = Session.builder()
                .sessionId("1")
                .date(LocalDateTime.now())
                .build();
        repository.save(sessionOne);

        repository.deleteById("1");

        assertThat(repository.findById("1")).isNull();

    }


    // TODO Add test to throw exception when sessionId is not available
}
