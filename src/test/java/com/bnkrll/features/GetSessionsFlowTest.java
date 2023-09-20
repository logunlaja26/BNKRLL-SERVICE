package com.bnkrll.features;

import com.bnkrll.JSONTestUtils;
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
