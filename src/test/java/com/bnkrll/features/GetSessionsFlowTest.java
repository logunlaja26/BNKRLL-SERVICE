package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
import com.bnkrll.model.GameType;
import com.bnkrll.model.Session;
import com.bnkrll.service.InMemorySessionRepository;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static com.bnkrll.model.GameType.TEXAS_HOLD_EM;
import static com.bnkrll.model.LimitType.NO_LIMIT;
import static com.bnkrll.model.LimitType.POT_LIMIT;
import static com.bnkrll.model.PayType.CASH;
import static com.bnkrll.model.PayType.TOURNEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GetSessionsFlowTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    InMemorySessionRepository repository;

    @Test
    void successfulGetSessionsRestCall() throws Exception {
        // save Sessions

        Session sessionOne = Session.builder()
                .sessionId("073cfb05-e65c-490a-9837-5083fae294b7")
                .payType(CASH)
                .gameType(TEXAS_HOLD_EM)
                .limitType(NO_LIMIT).buyin(BigDecimal.valueOf(200))
                .gameProfit(BigDecimal.valueOf(101.55))
                .location("Atlantic City")
                .date(LocalDateTime.of(2023, 1, 13,10,5))
                .totalAfterProfit(new BigDecimal("400.50").setScale(2, RoundingMode.HALF_UP))
                .build();

        Session sessionTwo = Session.builder()
                .sessionId("dfcc3b6d-63d1-4dba-b049-2a52cb784532")
                .payType(TOURNEY)
                .gameType(TEXAS_HOLD_EM)
                .limitType(POT_LIMIT).buyin(BigDecimal.valueOf(400))
                .gameProfit(BigDecimal.valueOf(102.00))
                .location("Las Vegas")
                .date(LocalDateTime.of(2023, 1, 11,10,5))
                .totalAfterProfit(new BigDecimal("300.75").setScale(2, RoundingMode.HALF_UP)).build();


        repository.save(sessionTwo);
        repository.save(sessionOne);

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
