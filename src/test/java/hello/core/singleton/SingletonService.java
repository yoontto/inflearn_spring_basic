package hello.core.singleton;

public class SingletonService {

    //1. static으로 올려놓으면 클래스레벨에 올라가므로 딱 하나만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 메소드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용해서 객체 생성을 못하게 막는다.
    private SingletonService() {
        
    }

    public void logic(){
        System.out.println("싱글톤 객체 호출 로직");
    }
    
}
