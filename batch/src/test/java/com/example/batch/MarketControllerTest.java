package com.example.batch;

import com.example.batch.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@Slf4j
class MarketControllerTest {

    @Autowired
    MarketRepository marketRepository;

    @Test
    @Transactional
    void dataTest(){
        List<Market> marketList = new ArrayList<>();

        Market apple = new Market(1, "apple", 1111);
        Market banana = new Market(1, "banana", 888);

        marketList.add(apple);
        marketList.add(banana);

        log.info("size: "+marketList.size());

        marketRepository.saveAll(marketList);

        List<Market> all = marketRepository.findAll();
        for(int i=0;i<all.size();i++){
            log.info("data: {}", all.get(i).toString());
        }
    }

}