package hello.advanced.trace.strategy.code.startegy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements Strategy{
    @Override
    public void call() {
        log.info("비즈니스 2 로직 실행 ");
    }
}
