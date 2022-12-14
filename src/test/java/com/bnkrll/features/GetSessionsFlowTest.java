package com.bnkrll.features;

import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepositorySimulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Matchers.anyInt;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class GetSessionsFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SessionRepositorySimulator repository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String SESSION_ID_ONE = "bb791404-78f0-11ed-a1eb-0242ac120002";
    private static final String SESSION_ID_TWO = "ada8881e-78f0-11ed-a1eb-0242ac120002";


    @Test
    public void getSessionsFromDatabase() throws Exception {
        Session sessionOne = Session.builder().sessionId(SESSION_ID_ONE).build();
        Session sessionTwo = Session.builder().sessionId(SESSION_ID_TWO).build();
        List<Session> listofSessions = new ArrayList<>(Arrays.asList(sessionOne,sessionTwo));
        repository.save(sessionOne);
        repository.save(sessionTwo);
        repository.getLastSessions(2);

        assertEquals(listofSessions.size(),repository.getLastSessions(2).size());
    }
}
