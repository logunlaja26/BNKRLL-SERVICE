package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
import com.bnkrll.model.Session;
import com.bnkrll.service.InMemorySessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.bnkrll.model.GameType.TEXAS_HOLD_EM;
import static com.bnkrll.model.LimitType.NO_LIMIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateSessionFlowTest {

    private static final String SESSION_ID = "073cfb05-e65c-490a-9837-5083fae294b7";
    @Autowired
    InMemorySessionRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void updateBuyIn_Success() throws Exception {
        Session sessionOne = Session.builder()
                .sessionId(SESSION_ID)
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT)
                .buyin(BigDecimal.valueOf(200))
                .build();
        repository.save(sessionOne);
        String expectedSessionsResponse = JSONTestUtils.readFile("updateBuyInRequest.json");

        mockMvc.perform(patch("/api/session/buy-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(expectedSessionsResponse))
                .andExpect(status().isOk());

        assertThat(repository.findById(SESSION_ID).get().getBuyin()).isEqualTo(BigDecimal.valueOf(400));


    }


}
