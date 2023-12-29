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
import java.util.ArrayList;
import java.util.List;

import static com.bnkrll.model.GameType.TEXAS_HOLD_EM;
import static com.bnkrll.model.LimitType.NO_LIMIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateSessionFlowTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String SESSION_ID = "073cfb05-e65c-490a-9837-5083fae294b7";
    @Autowired
    InMemorySessionRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void updateSession_Success() throws Exception {
        Session sessionOne = Session.builder()
                .sessionId(SESSION_ID)
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT)
                .buyin(BigDecimal.valueOf(200))
                .build();

        Session updatedSession = Session.builder()
                .sessionId("SESSION_ID")
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT)
                .buyin(BigDecimal.valueOf(400))
                .build();

        repository.save(sessionOne);
        List<Session> originalSession = new ArrayList<>();
        originalSession.add(sessionOne);
        assertThat(repository.getLastSessions(1)).isEqualTo(originalSession);

        String result = mockMvc.perform(patch("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(OBJECT_MAPPER.writeValueAsBytes(updatedSession)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyin").value(400))
                .andReturn().getResponse().getContentAsString();

        String expectedSessionsResponse = JSONTestUtils.readFile("expectedUpdateLastSessions.json");

       assertEquals(expectedSessionsResponse, result, JSONCompareMode.STRICT);



    }


}
