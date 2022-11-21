package com.example.batch.redis;

import com.example.batch.entity.Person;
import com.example.batch.repository.PersonRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
public class RedisTest {
    @Autowired
    PersonRedisRepository personRedisRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void entityTest() {
        Person person = new Person("tlrruddbs","ks", 30);
        log.info("person before: {}",person.toString());
        personRedisRepository.save(person);
        log.info("person before2: {}",person.toString());
        Person person1 = personRedisRepository.findById(person.getId()).get();
        log.info("person is: {}", person1.toString());
        System.out.println(person1.toString());

        personRedisRepository.count();
        personRedisRepository.delete(person1);
    }

    @Test
    void variableTest(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "tlrruddbs1";

        valueOperations.set(key, "vvaalluuee");

        String value = valueOperations.get(key);
        assertThat(value).isEqualTo("vvaalluuee");

    }

}
