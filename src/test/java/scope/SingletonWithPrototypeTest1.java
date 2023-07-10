package scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);

    }

    
    //싱글톤과 프로토타입을 같이 사용할때 : 싱글톤 객체를 생성할때 프로토타입 객체도 의존관계 주입이 됨.
    //싱글톤 빈과 계속 함께 유지됨 -> 프로토타입이 싱글톤처럼 동작함
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
        /*
        private final PrototypeBean prototypeBean;  //생성시점에 주입

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
        */

        //싱글톤 + 프로토타입 일때 프로토타입 각각 생성하게 만들기 1
        //사용할때마다 스프링 빈에 새로 요청
        /*
        @Autowired
        private ApplicationContext ac;
        */

        //싱글톤 + 프로토타입 일때 프로토타입 각각 생성하게 만들기 2
        //지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스를 제공하는 Provider
        //DL : Dependency Lookup
        /*@Autowired
        ObjectProvider<PrototypeBean> prototypeBeansProvider;*/

        //싱글톤 + 프로토타입 일때 프로토타입 각각 생성하게 만들기 3
        //JSR-330 프로바이더 사용 (javax)
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
            /*방법 1
             PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
             */

            //방법 2
            //PrototypeBean prototypeBean = prototypeBeansProvider.getObject();

            //방법3
            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
