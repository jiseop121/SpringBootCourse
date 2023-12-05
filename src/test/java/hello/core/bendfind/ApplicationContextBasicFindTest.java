package hello.core.bendfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanWithoutName(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("구체(구체화 클래스) 타입으로 조회")
    void findBeanBySpecific(){
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("[실패] 빈 이름으로 조회")
    void failFindBean(){
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("XX",MemberService.class));
    }
    //


//    @Test
//    @DisplayName("모든 빈 출력")
//    void findAllBean(){
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        System.out.println("ApplicationContextInfoTest.findAllBean");
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("bean = " + bean);
//        }
//    }

}
