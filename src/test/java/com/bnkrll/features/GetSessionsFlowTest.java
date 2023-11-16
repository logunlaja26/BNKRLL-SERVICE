package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
import com.bnkrll.service.InMemorySessionRepository;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
    void successfulGetSessionsFromDatabase() throws Exception {
        // save Sessions
        // TODO: repository.save(session)
        // "sessionId": "073cfb05-e65c-490a-9837-5083fae294b7",
        //    "payType": "CASH",
        //    "gameType": "TEXAS_HOLD_EM",
        //    "limitType": "NO_LIMIT",
        //    "gameProfit": "100.00",
        //    "location": "Atlantic City",
        //    "date": "01-13-2023",
        //    "totalAfterProfit": "200.00"
        //  },
        //  {
        //    "sessionId": "dfcc3b6d-63d1-4dba-b049-2a52cb784532",
        //    "payType": "TOURNEY",
        //    "gameType": "TEXAS_HOLD_EM",
        //    "limitType": "POT_LIMIT",
        //    "gameProfit": "100.00",
        //    "location": "Las Vegas",
        //    "date": "01-11-2023",
        //    "totalAfterProfit": "100.00"

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
