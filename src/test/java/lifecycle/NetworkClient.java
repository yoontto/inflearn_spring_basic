package lifecycle;

//javax는 자바에서 지원함! 스프링이 아닌 다른 컨테이너에서도 동작함
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{

    private String url;

    /*
     <스프링 빈의 이벤트 라이프사이클>
        스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입
        -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링종료
     */

    public NetworkClient() {
        //객체 생성과 초기화 분리하자!
        //생성자는 필수정보를 받고 객체를 생성함.
        //초기화는 새성된 값들을 이용해 외부 커넥션 만드는 등 무거운 작업 수행
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url){
        //객체 생성 후 setter 호출됨
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String msg) {
        System.out.println("call : " + url + " msg = " + msg);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    //@PostConstruct, @PreDestroy
    // 결론 : 어노테이션 쓰기!
    // 단점 : 외부 라이브러리에서는 사용 못함, 외부 라이브러리 초기화,종료는 @Bean의 기능을 사용해야 함
    @PostConstruct
    public void init() {
        System.out.println("init");
        connect();
        call("초기화 완료");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("close");
        disconnect();
    }
}
