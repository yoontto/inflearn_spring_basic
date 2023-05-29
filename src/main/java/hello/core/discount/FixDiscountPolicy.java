package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  //RateDiscountPolicy도 component 스캔해줘서 빈이 2개 선언된 상태
//No qualifying bean of type 'hello.core.discount.DiscountPolicy'
//available: expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인
    
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
