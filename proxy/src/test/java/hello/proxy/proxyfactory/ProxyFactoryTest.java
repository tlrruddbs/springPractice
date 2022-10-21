package hello.proxy.proxyfactory;

import hello.proxy.common.ConcreteService;
import hello.proxy.common.ServiceImpl;
import hello.proxy.common.ServiceInterface;
import hello.proxy.common.advice.TimeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 jdk 동적 프록시사용")
    void interfaceProxy(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        log.info("target class = {}", target.getClass());
        log.info("proxy class = {}", proxy.getClass());
        proxy.save();
        log.info("----");
        proxy.find();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    }


    @Test
    @DisplayName("구체 클레스만 있으면 있으면 CGLIB 프록시사용")
    void concreteProxy(){
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService)proxyFactory.getProxy();
        log.info("target class = {}", target.getClass());
        log.info("proxy class = {}", proxy.getClass());
        proxy.call();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    }
}
