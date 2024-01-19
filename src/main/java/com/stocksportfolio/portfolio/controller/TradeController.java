//package com.stocksportfolio.portfolio.controller;
//
//import com.stocksportfolio.portfolio.model.TradeType;
//import com.stocksportfolio.portfolio.response.TradeResponse;
//import com.stocksportfolio.portfolio.service.TradeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TradeController {
//
//    @Autowired
//    private TradeService tradeService;
//
//    @PostMapping("/api/trades")
//    public ResponseEntity<TradeResponse> executeTrade(
//            @RequestParam Long userAccountId,
//            @RequestParam TradeType tradeType,
//            @RequestParam Integer quantity,
//            @RequestParam Long stockId
//    ) {
//        TradeResponse tradeResponse = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);
//        return ResponseEntity.ok(tradeResponse);
//    }
//}

package com.stocksportfolio.portfolio.controller;

import com.stocksportfolio.portfolio.model.TradeType;
import com.stocksportfolio.portfolio.response.TradeResponse;
import com.stocksportfolio.portfolio.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/api/trades")
    public ResponseEntity<TradeResponse> executeTrade(
            @RequestParam Long userAccountId,
            @RequestParam TradeType tradeType,
            @RequestParam Integer quantity,
            @RequestParam Long stockId
    ) {
        TradeResponse tradeResponse = tradeService.executeTrade(userAccountId, tradeType, quantity, stockId);
        return ResponseEntity.ok(tradeResponse);
    }
}
