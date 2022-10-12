package hello.advanced.trace.strategy.code;

import hello.advanced.trace.strategy.code.startegy.ContextV2;
import hello.advanced.trace.strategy.code.startegy.StrategyLogic1;
import hello.advanced.trace.strategy.code.startegy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    //전략패턴
    @Test
    void strategyV1(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }
}
