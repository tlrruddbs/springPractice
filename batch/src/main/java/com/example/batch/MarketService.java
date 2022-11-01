package com.example.batch;

import com.example.batch.repository.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;

    public List<Market> findAll(){
        return marketRepository.findAll();
    }

    public void insert(List<Market> marketList) {
        marketRepository.saveAll(marketList);
    }
}
