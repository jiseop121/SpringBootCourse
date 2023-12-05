package hello.core.discount;
import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10퍼 할인이 적용되어야한다.")
    void vip_o(){
        //given
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    //테스트는 실패 테스트도 항상 만들어야한다.
    @Test
    @DisplayName("VIP가 아니면 할인ㅇ니 적용되지 않는다.")
    void vip_x(){
        //given
        Member member = new Member(1L,"memberVIP", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
}