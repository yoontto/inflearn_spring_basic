package hello.core.singleton;

public class StatefulService {

    // price라는 변수를 필드에서 공유하기 때문에
    // price를 메소드 내부에 선언하기
    private int price;   //상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name=" + name + " price=" +price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    /*
    지역변수 선언해준 싱글톤 문제 해결한 코드 :: 위의 코드들은 다 주석처리하고 실험할 것
    public int rightOrder(String name, int price){
        int price2 = price;
        System.out.println("name=" + name + " price=" +price);
        return price2;
    }
    */



    
}
