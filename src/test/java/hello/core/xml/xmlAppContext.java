package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class xmlAppContext {

    @Test
    void xmlAppContext(){
        //xml 파일의 설정을 가지고 스프링 컨테이너를 생성
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        //테스트 결과 출력
        //DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory 
        //- Creating shared instance of singleton bean 'memberService'

    }
}
