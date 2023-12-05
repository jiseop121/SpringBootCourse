package hello.core.bendfind;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindText {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("빈 이름 없이 타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생")
    void test1(){
        assertThrows(NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("중복 일때 빈 이름을 지정하면 오류 없음")
    void test2(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기")
    void test3(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = " + key);
            System.out.println("beansOfType value= " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    //중복 오류 경우의 config 생성
    @Configuration
    static class SameBeanConfig{

        //두 매서드의 주소가 똑같이 나오는 이유는 싱글톤 패턴에 기인한다.
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
