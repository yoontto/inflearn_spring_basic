package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        void statefulServiceSingleton() {
            AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
            //statefulService1 == statefulService2 같음
            StatefulService statefulService1 = ac.getBean(StatefulService.class);
            StatefulService statefulService2 = ac.getBean(StatefulService.class);

            statefulService1.order("userA", 10000);
            statefulService2.order("userB", 20000);

            //A 주문금액 조회  :: 결과 20000원 나옴
            int price1 = statefulService1.getPrice();
            System.out.println("price1 = " + price1);

            Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


            //지역변수 선언해준 싱글톤 문제 해결한 코드
            //int price1 = statefulService1.rightOrder("userA", 10000);
            //System.out.println(price1);

        }
    }
}