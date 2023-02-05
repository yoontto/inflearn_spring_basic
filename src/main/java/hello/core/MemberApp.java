package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //생성자 직접 생성하지 않고 AppConfig로 주입시켜서 사용
        //MemberService memberService = new MemberServiceImpl();

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // ApplicationContext : 스프링 컨테이너
        // AppConfig에 있는 환경설정을 가지고 스프링 컨테이너에서 필요한 정보를 가져온다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메소드명, 반환타입

        //가입한 멤버
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //저장된 멤버
        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
