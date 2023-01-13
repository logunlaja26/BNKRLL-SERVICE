package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
import com.bnkrll.model.Session;
import com.bnkrll.service.SessionRepositorySimulator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GetSessionsFlowTest {


    private static final String SESSION_ID_ONE = "bb791404-78f0-11ed-a1eb-0242ac120002";
    private static final String SESSION_ID_TWO = "ada8881e-78f0-11ed-a1eb-0242ac120002";
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SessionRepositorySimulator repository;


    @Test
    void getSessionsFromDatabase() throws Exception {
        Session sessionOne = Session.builder().sessionId(SESSION_ID_ONE).build();
        Session sessionTwo = Session.builder().sessionId(SESSION_ID_TWO).build();
        List<Session> listofSessions = Arrays.asList(sessionOne, sessionTwo);
        assertEquals(listofSessions.size(), repository.getLastSessions(2).size());

    }

    @Test
    void successfulGetSessionsFromDatabase() throws Exception {
        String actualSessionsResponse = mockMvc.perform(get("/api/session")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedSessionsResponse = JSONTestUtils.readFile("expectedGetLastSessions.json");

        JSONAssert.assertEquals(expectedSessionsResponse, actualSessionsResponse, JSONCompareMode.STRICT);
    }
}
