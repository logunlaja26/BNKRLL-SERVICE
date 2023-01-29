package com.bnkrll.features;

import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepositorySimulator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class SessionRepositoryTest {
    private static final String SESSION_ID_ONE = "bb791404-78f0-11ed-a1eb-0242ac120002";
    private static final String SESSION_ID_TWO = "ada8881e-78f0-11ed-a1eb-0242ac120002";

    @Autowired
    private SessionRepositorySimulator repository;

    @Test
    void getSessionsFromDatabase() {
        Session sessionOne = Session.builder().sessionId(SESSION_ID_ONE).build();
        Session sessionTwo = Session.builder().sessionId(SESSION_ID_TWO).build();
        List<Session> listofSessions = Arrays.asList(sessionOne, sessionTwo);
        assertEquals(listofSessions.size(), repository.getLastSessions(2).size());

    }

}
