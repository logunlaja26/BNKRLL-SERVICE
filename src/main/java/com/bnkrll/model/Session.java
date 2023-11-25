package com.bnkrll.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId = UUID.randomUUID().toString();
    @JsonProperty("paytype")
    private PayType payType;
    @JsonProperty("gametype")
    private GameType gameType;
    @JsonProperty("limittype")
    private LimitType limitType;
    @JsonProperty("profit")
    private BigDecimal gameProfit;
    @JsonProperty("location")
    private String location;
    @JsonProperty("date")
    private LocalDate date;
    private BigDecimal totalAfterProfit;

}
