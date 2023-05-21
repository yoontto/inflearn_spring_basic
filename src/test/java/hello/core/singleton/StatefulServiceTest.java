package hello.core.singleton;

import org.assertj.core.api.Assertions;
<<<<<<< HEAD
import org.junit.jupiter.api.DisplayName;
=======
>>>>>>> origin/master
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
<<<<<<< HEAD
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);
        System.out.println("price = " + userBPrice);

        Assertions.assertThat(userAPrice).isNotSameAs(userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

=======
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

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
>>>>>>> origin/master
    }
}