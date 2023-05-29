package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {

    //아래 적힌 SameBeanConfig 테스트 클래스로 실험
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByTypeDuplicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        //No qualifying bean of type 'hello.core.member.MemberRepository' available:
        //expected single matching bean but found 2: memberRepository,memberRepository2
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다!")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository2", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입을 모두 조회하기")
    void findAllBeanByType(){
        // 특정타입이 2개 이상일 경우 자동으로 Map으로 가져옴
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : beansOfType.keySet()){
            System.out.println("key = "+key + "value = "+ beansOfType.get(key));
        }
        System.out.println("beansOfType = "+ beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    //static으로 선언해주는 건 스코프를 ApplicationContextSameBeanFindTest 
    //안에서만 쓰겠다는 것임
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }


    }
}
