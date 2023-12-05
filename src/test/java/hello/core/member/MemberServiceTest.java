package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join(){
        //given : 이러한 환경이 주어졌을때
        Member member = new Member(1L, "memberA",Grade.VIP);

        //when : 이러한 가정이 적용되면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이러한 결과가 나온다
        Assertions.assertEquals(findMember,member);
    }
}
