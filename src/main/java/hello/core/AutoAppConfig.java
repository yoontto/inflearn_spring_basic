package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
//자동 빈 주입 :: Component 어노테이션이 붙은 애들을 자동으로 빈등록 해줌
//이렇게 되면 의존관계 주입을 해주지 않음 -> qutowired 처리해서 사용
@ComponentScan(
        // 컴포넌트 스캔 제외할 것 설정
        // Appconfig 파일, TestConfig 등에 @Configuration 달려있어서 충돌날까봐 예외처리
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
