package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    //어노테이션 기반의 스프링 컨테이너
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBeans(){
        //모든 빈의 이름 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            //name = 키, object = 밸류
            System.out.println("name="+beanDefinitionName + "/ object=" + bean);
        }
    }


    // 내가 등록한 빈들 출력
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBeans(){
        //모든 빈의 이름 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            //getBeanDefinition : 빈에 대한 메타데이터 반환
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name=" + beanDefinitionName + " / object=" + bean);
            }
        }
    }
}
