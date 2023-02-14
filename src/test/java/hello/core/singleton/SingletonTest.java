package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    
    //스프링 컨테이너는 알아서 자동으로 싱글톤으로 객체를 생성해줌
    
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //요청이 올때마다 새 객체를 생성해서 돌리는 것은 메모리 낭비가 심함
        //싱글톤 : 객체를 한개만 생성하고 공유하도록 설계

        //1.조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2.조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println(memberService1);
        System.out.println(memberService2);
        
        //1은 2랑 다름
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    public static void main(String[] args) {
        //싱글톤 객체 생성 안됨
        //SingletonService singlrtonService1 = new SinglrtonService();
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1);
        System.out.println(singletonService2);

        //1 == 2
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }
}
