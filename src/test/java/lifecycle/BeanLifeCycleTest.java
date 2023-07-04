package lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        //부모 ConfigurableApplicationContext, 자식 AnnotationConfigApplicationContext
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        //destroyMethod는 추론기능이 있음(inferred)
        //close나 shutdown이라는 이름의 메소드를 자동으로 호출해줌
        //destroyMethod = "" 이렇게 빈칸으로 두면 추론기능 동작 안함
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://fake.dev");
            return networkClient;
        }
    }
}
