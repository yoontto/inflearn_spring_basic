package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
// 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
// Configuration : 애플리케이션 설정정보
@Configuration
public class AppConfig {

    //생성자를 만들어 주입해 줌
    /*public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }*/
    // 리팩토링!
    // 위의 코드에서는 중복이 있고, 역할에 따른 구현이 잘 보이지 않는다.
    //new MemoryMemberRepository() 이 부분이 중복

    //스프링 컨테이너(ApplicationContext)는 @Configuration이 붙은 설정정보를 사용
    // 여기서 Bean이 붙은 메소드를 전부 호출해 반환된 객체를 컨테이너에 등록 (스프링 빈이 됨)
    @Bean
    public MemberService memberService() { // 메소드명 : 역할 (인터페이스)
        System.out.println("call MemberService");
        return new MemberServiceImpl(memberRepository());   //내부 : 구현
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call OrderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
