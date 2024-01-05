package com.bnkrll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId;
    private PayType payType;
    private GameType gameType;
    private LimitType limitType;
    private BigDecimal buyin;
    private BigDecimal gameProfit;
    private String location;
    private LocalDateTime date = LocalDateTime.now();
    private BigDecimal totalAfterProfit;

}
