package com.study.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@SpringBootTest
public class LambdaTest {
    public LambdaTest() {
    }

    @Test
    void lambda() {
        Math plusLambda = (first, second) -> first + second;
        System.out.println(plusLambda.calc(4, 2));

        Math minusLambda = (first, second) -> first - second;
        System.out.println(minusLambda.calc(4, 2));
    }
    @Test
    void intFunction(){
        IntFunction intSum = (x) -> x+2;
        System.out.println(intSum.apply(1));

    }
    @Test
    void stringFunction(){
        BinaryOperator stringSum=(x, y)->x+" "+y;
        System.out.println(stringSum.apply("hello","world"));
    }
    @Test
    void stream(){
        IntStream.range(1, 11 ).filter(i-> i%2==0)
                .forEach(System.out::println);

        System.out.println(
                IntStream.range(0, 1001)
                        .skip(500)
                        .filter(i-> i%2==0)
                        .filter(i-> i%5==0)
                        .sum()
        );
    }
}
