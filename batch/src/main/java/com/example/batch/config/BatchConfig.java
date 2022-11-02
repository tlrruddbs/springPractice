package com.example.batch.config;

import com.example.batch.Market;
import com.example.batch.MarketResult;
import com.example.batch.repository.MarketRepository;
import com.example.batch.repository.MarketResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final MarketResultRepository marketResultRepository;

    //exampleJob  생성
    @Bean
    public Job exampleJob() throws Exception {
        return jobBuilderFactory.get("exampleJob")
                .start(exampleStep()).build();
    }

    //exampleStep 생성
    @Bean
    @JobScope
    public Step exampleStep() throws Exception {
        return stepBuilderFactory.get("exampleStep")
                .<Market, Market>chunk(10)
                .reader(reader(null))
                .processor(processor(null))
                .writer(writer(null))
                .build();
    }

    /*
    tasklet example
     */
    @Bean
    @StepScope
    public JpaPagingItemReader<Market> reader(@Value("#{jobParameters[requestDate]}") String requestDate) throws Exception {
        log.info("==> reader value : " + requestDate);

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("price", 0);

        return new JpaPagingItemReaderBuilder<Market>()
                .parameterValues(parameterValues)
                .queryString("SELECT m FROM Market m WHERE m.price = : price")
                .entityManagerFactory(entityManagerFactory)
                .name("JpaPagingItemReader")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Market, Market> processor(@Value("#{jobParameters[requestDate]}")String requestDate){
        return new ItemProcessor<Market, Market>() {
            @Override
            public Market process(Market market) throws Exception {
                log.info("-->processor Market : "+market);
                log.info("-->processor value : "+requestDate);

                LocalDateTime now = LocalDateTime.now();
                if(market.getPrice()==0){
                    MarketResult marketResult = new MarketResult(market.getName(), now.toString(), market.getId());

                    log.info("marketResult = "+marketResult.toString());

                    marketResultRepository.save(marketResult);
                    market.setPrice(1);
                }
                //100원 추가
                //market.setPrice(market.getPrice()+10000);
                return market;
                
                
            }
        };
    }

    @Bean
    @StepScope
    public JpaItemWriter<Market> writer(@Value("#{jobParameters[requestDate]}") String requestDate){
        log.info("--> writer value : "+requestDate);

        return new JpaItemWriterBuilder<Market>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

}
