package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
import com.bnkrll.model.Session;
import com.bnkrll.service.InMemorySessionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static com.bnkrll.model.GameType.TEXAS_HOLD_EM;
import static com.bnkrll.model.LimitType.NO_LIMIT;
import static com.bnkrll.model.LimitType.POT_LIMIT;
import static com.bnkrll.model.PayType.CASH;
import static com.bnkrll.model.PayType.TOURNEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UpdateSessionsFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    InMemorySessionRepository repository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String SESSION_ID = "073cfb05-e65c-490a-9837-5083fae294b7";

    @Test
    void updateSession_Success() throws Exception {

        Session sessionOne = Session.builder()
                .sessionId(SESSION_ID)
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT)
                .buyin(BigDecimal.valueOf(200))
                .build();

        Session updatedSession = Session.builder()
                .sessionId(SESSION_ID)
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT).buyin(BigDecimal.valueOf(400))
                .build();

        //Save initially in db
        repository.save(sessionOne);

        //retrieve from db
        assertThat(repository.findById(SESSION_ID)).isEqualTo(sessionOne);

        //update the session object from retrieval
        String actualSessionsResponse =
         mockMvc.perform(patch("/api/session")
                        .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsBytes(updatedSession)))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andReturn()
                 .getResponse()
                 .getContentAsString();

        //verify update
        String expectedSessionsResponse = JSONTestUtils.readFile("expectedUpdateLastSessions.json");

        JSONAssert.assertEquals(expectedSessionsResponse, actualSessionsResponse, JSONCompareMode.STRICT);
    }
}
