package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.startegy.ContextV1;
import hello.advanced.trace.strategy.code.startegy.Strategy;
import hello.advanced.trace.strategy.code.startegy.StrategyLogic1;
import hello.advanced.trace.strategy.code.startegy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }
    private void logic2(){
        long startTime = System.currentTimeMillis();

        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    //전략 패턴 사용
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스1 로직 실행!");
            }
        });
        contextV1.execute();

        ContextV1 context2 = new ContextV1(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스1 로직 실행!");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4(){


        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스1 로직 실행!"));
        contextV1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스2 로직 실행!"));
        context2.execute();
    }
}
