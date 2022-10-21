package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class BasicTest {
    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        A beanA = applicationContext.getBean("beanA", A.class);
        beanA.helloA();

        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));

    }

    @Configuration
    static class BasicConfig{
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

        @Bean(name = "beanB")
        public B qwe(){
            return new B();
        }
    }

    static class A{
        public void helloA(){
            log.info("hello A");
        }
    }

    static class B{
        public void helloB(){
            log.info("hello B");
        }
    }
}
