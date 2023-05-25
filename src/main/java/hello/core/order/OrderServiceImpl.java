package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//클라이언트 OrderServiceImpl
@Component
public class OrderServiceImpl implements OrderService{

    //현재 인터페이스, 구체 두군데 다 의존하면서 DIP ( 의존관계 역전 원칙  ) 위반하고 있음
    // 다른 구체로 변경하는 행위도 OCP( 개방-폐쇄 원칙 ) 위반!
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired  // 생성자 주입 :: 생성자가 딱 하나일때는 autowired 붙은거랑 똑같음
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    //의존관계 자동주입 방법 4가지
    // 생성자 주입, 수정자 주입(setter), 필드주입 (commonMethod처럼), 일반메소드 주입
    // 생성자 주입 :: 불변하는 의존관계에 사용
    // 수정자 주입 :: 선택, 변경 가능성 있는 의존관계에 사용
    // 필드 주입 :: 외부에서 변경할 수 없기때문에 특수한 목적을 가진 빈 외에는 사용하지 않음
    // 일반메소드 주입 :: 잘 사용하지 않음


    // SRP 단일 책임원칙에 맞게 잘 설계됨
    // 만약 할인정책에 문제 있으면 member 관계 없이 discountPolicy 내부만 수정하면 됨
    // 만약 멤버에 문제 있으면 discountPolicy 관계 없이 member 내부만 수정하면 됨
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //memberGrade만 넘기지 않고 member를 통으로 넘긴 이유?
        // -> 미래의 확장성 부분 등을 고려해서!
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
