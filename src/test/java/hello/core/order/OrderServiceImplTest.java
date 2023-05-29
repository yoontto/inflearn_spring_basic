package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    //7.의존관계 주입 :: 생성자 주입을 선택해라!
    @Test
    void createOrder(){
        // 1번 테스트 :: setMemberRepository, setDiscountPolicy 사용할 때
        /*
         setXxx를 진행했을 때 NPE가 난다.
         createOrder를 진행할때 의존관계가 주입되어 있어야 하는데,
         setter의 경우 컴파일이 다 진행된 후 주입되는 순서이므로 실행 순서가 더 늦기 때문에
         의존관계 자동 주입이 이루어지지 않은 상태로 진행되기때문에 NPE 발생
        */
        /*OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L, "itemA", 10000);*/



        //2번 테스트 :: 생성자로 주입할때
        /*
          *** 순수한 자바코드로 테스트 진행 ***
          new OrderServiceImpl에 파라미터 없이 진행하면 컴파일 에러가 뜬다.
          Id가 1L인 멤버가 MemoryMemberRepository에 저장되지 않은 상태이므로
          새로운 메모리객체를 만들어서 임의로 회원을 넣어준다.
          그 객체로 테스트를 진행하면 성공!
         */
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "sam", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
