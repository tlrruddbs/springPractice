package com.example.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MarketController {
    private final MarketService marketService;

    @GetMapping("ks")
    public String ks() throws Exception{
        log.info("log info!!!");
        log.warn("log warn!!!");
        log.error("log.error");
        return "success!!";
    }
    @GetMapping("test")
    public String test() throws Exception{

        List<Market> list = marketService.findAll();
        log.info("sizesize: "+list.size());
        return null;
    }

    @GetMapping("insert")
    public void insert(){

        List<Market> marketList = new ArrayList<>();

        Market market = new Market();

        for(int i=1;i<=100;i++){
             market.setId(i);
             market.setName("product"+i);
             market.setPrice(0);
             marketList.add(market);
        }
        marketService.insert(marketList);
    }
}
