package com.bnkrll.features;

import com.bnkrll.model.Session;
import com.bnkrll.service.InMemorySessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class SaveSessionFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemorySessionRepository repository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String SESSION_ID = "8edac816-30ee-4469-bfe5-226b6e32dd8f";

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void savesSessionInDatabase() throws Exception {
        Session newSession = Session.builder()
                .sessionId(SESSION_ID)
                .build();

        mockMvc.perform(post("/api/session/save")
                        .content(OBJECT_MAPPER.writeValueAsString(newSession))
                        .contentType(APPLICATION_JSON))
                .andDo(print());

        assertThat(repository.findById(SESSION_ID))
                .isEqualTo(newSession);
    }



}
