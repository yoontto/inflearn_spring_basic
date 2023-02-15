package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("서로 다른 서비스에서 중복되게 호출해도 같은 객체로 싱글톤 유지하는지 검증")
    void configurationTest(){
        //각 메소드에 sysout 찍어서 확인해본 결과
        // 예상대로라면 3번 호출되어야 할 MemberRepository가 1번만 호출되고 있음
        
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //구체 클래스 꺼내와서 테스트 중 :: 별로 안 좋아요~^^
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    void configurationDeep() {
        //AnnotationConfigApplicationContext으로 AppConfig 넘기면 
        //AppConfig도 빈으로 등록되어 조회 가능하게 됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ee2b784e
        // 스프링 빈으로 등록되는 과정에서 
        // CGLIB라는 바이트코드를 조작 라이브러리로 다른 클래스를 하나 만듦
        // 이 라이브러리 덕분에 싱글톤이 보장됨
        // 빈 있으면 ? 기존 빈 사용 : 새로운 빈 생성

        // **** @Configuration 없으면? ****
        //CGLIB 기술 안 씀  -> 싱글톤 꺠짐
        //bean = class hello.core.AppConfig
        //스프링 컨테이너에서 관리해주지 않음

        //@Autowired로 의존관계를 주입하면 문제가 해결됨
        //스프링 설정 정보에는 @Configuration 꼭 넣기
    }
}
