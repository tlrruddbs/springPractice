package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class BasicPostProcessorTest {
    @Test
    void basicConfig() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        B beanB = applicationContext.getBean("beanA", B.class);
        beanB.helloB();

        assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));

    }

    @Configuration
    static class BeanPostProcessorConfig{
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

        @Bean
        public AtoBPostProcessor helloPostProcessor(){
            return new AtoBPostProcessor();
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

    static class AtoBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

           log.info("BeanName={} bean={}", beanName, bean);
           if(bean instanceof A){
               return new B();
           }
           return bean;
        }
    }
}
