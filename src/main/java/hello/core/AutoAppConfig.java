package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
//ComponentScan :: Component 어노테이션이 붙은 애들을 자동으로 빈등록 해줌
//컴포넌트 스캔을하면 설정정보를 기재해주지 않으므로 의존관계 주입을 해주지 않음 -> autowired 처리해서 사용
//@Autowired :: 스캔을 통해 bean으로 등록된 클래스들의 타입을 조회해 타입이 맞는 것을 알아서 넣어줌
// ex > MemberRepository가 들어가야 하는데, MemoryMemberRepository가 빈으로 등록이 되어있음
//      상속받은 타입이 같으므로 알아서 MemoryMemberRepository 를 찾아서 의존관계 주입해줌
@ComponentScan(

        // basePackages : 스캔을 시작할 패키지 위치를 지정해 모든 자바코드 뒤질 필요 없이 만듦
        basePackages = "hello.core.member",

        //basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다
        basePackageClasses = AutoAppConfig.class,
        
        //요즘은 basePackages, basePackagesClasses 지정 안함
        //Config파일을 프로젝트 최상단에 위치시킴

        // excludeFilters :: 컴포넌트 스캔 제외할 것 설정
        // AppConfig 파일, TestConfig 등에 @Configuration 달려있는데,
        // Configuration 클래스 내부 보면 @Component 달려있음
        // 충돌날까봐 예외처리 (보통은 설정 정보를 제외하지 않지만, 기존 예제코드 살리려고 선택..!)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 등록 vs 자동 빈 등록 충돌시에는??
    // 수동 빈을 우선해줌 : 수동빈이 자동빈을 오버라이딩함
    //Overriding bean definition for bean 'memoryMemberRepository' with a different definition:replacing 
    
    //근데 너무 애매한 에러를 많이 발생시키는 상황이라 스프링부트에서는 허용하지 않음
    //스프링부트인 CoreApplication 실행하면 충돌나고 오류 나게 설정되어 있음
    //오버라이딩 허용할거면 application.properties 에 지정해주면 되는데,,, 그래도 안하는게 좋음
    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository () {
        return new MemoryMemberRepository();
    }


}
