package hello.core.bendfind;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac= new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 , 자식이 둘 이상 있으면 중복 오류")
    void test1(){
//        DiscountPolicy bean =
        assertThrows(NoUniqueBeanDefinitionException.class,
        () ->  ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 , 자식이 둘 이상 있으면 빈 이름을 지정")
    void test2(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회 [추천 X]")
    void test3(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void test4(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        beansOfType.entrySet().stream()
                .forEach((entry)-> {
                    System.out.println("key = " + entry.getKey()+"value = "+entry.getValue());
                });
    }

    @Test
    @DisplayName("Oject타입 (최상위 타입)으로 모두 조회하기")
    void test5(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        beansOfType.entrySet().stream()
                .forEach(entry -> System.out.println("key = " + entry.getKey()+"key = " + entry.getValue()));
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
