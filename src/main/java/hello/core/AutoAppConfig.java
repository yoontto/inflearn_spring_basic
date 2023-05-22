package hello.core;

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
        // excludeFilters :: 컴포넌트 스캔 제외할 것 설정
        // AppConfig 파일, TestConfig 등에 @Configuration 달려있는데,
        // Configuration 클래스 내부 보면 @Component 달려있음
        // 충돌날까봐 예외처리 (보통은 설정 정보를 제외하지 않지만, 기존 예제코드 살리려고 선택..!)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
