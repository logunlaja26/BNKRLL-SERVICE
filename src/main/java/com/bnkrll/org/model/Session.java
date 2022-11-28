package com.bnkrll.org.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String sessionId;

    //  TODO: add additional session columns
    //    payType : PayType [CASH, TOURNEY]
    //    gameType : GameType [TEXAS HOLD’EM]
    //    limitType : LimitType [NO_LIMIT, POT_LIMIT]
    //    gameProfit : BigDecimal ($200)
    //    location : String
    //    date : LocalDate (mm/dd/yyyy)
    //    totalAfterProfit : BigDecimal ($1400)

}
