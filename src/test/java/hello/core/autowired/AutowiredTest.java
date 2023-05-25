package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    // 의존관계 자동 주입의 옵션에 관해서 알아보기 위한 테스트
    // 테스트 클래스를 생성해서 호출해보는 간단한 실험
    // Member 클래스는 스프링 빈으로 등록되지 않은 클래스이기 때문에 의존관계 주입을 할 수 없음
    // 그런데 의존관계 주입을 시도하면?
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //required 옵션은 기본적으로 true 인데 false로 설정하면 메서드 자체가 호출 안됨
        //호출 안됨 (자동 주입할 대상이 없어서 메서드 자체가 호출 안됨)
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1=" + noBean1);
        }

        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2=" + noBean2);
        }

        //Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3=" + noBean3);
        }

    }
}
