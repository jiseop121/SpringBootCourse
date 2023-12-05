package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // AppConfig에 있는 설정 정보를 가지고 annotation(Bean)이 붙은 것을 다 관리해주는 역할
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //name은 메서드 이름
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(123L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(123L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
