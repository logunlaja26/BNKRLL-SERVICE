package com.bnkrll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId;
    private PayType payType;
    private GameType gameType;
    private LimitType limitType;
    private BigDecimal gameProfit;
    private String location;
    private LocalDate date;
    private BigDecimal totalAfterProfit;

}
