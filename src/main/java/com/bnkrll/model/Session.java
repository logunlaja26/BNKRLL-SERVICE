package com.bnkrll.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId = UUID.randomUUID().toString();
    private PayType payType;
    private GameType gameType;
    private LimitType limitType;
    private BigDecimal gameProfit;
    private String location;
    private LocalDateTime date = LocalDateTime.now();
    private BigDecimal totalAfterProfit;

}
