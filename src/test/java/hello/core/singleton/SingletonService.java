package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    //jvm에서 객체를 미리 생성한다

    public static SingletonService getInstance() {
        return instance;
    }//객체의 인스턴스가 필요하면 -> 조회할떄 사용한다 -> 늘 같은 인스턴스 반환

    private SingletonService() {//생성자 생성 private -> 외부에서 생성 못함

    }

    public void logic() {
        System.out.println("싱글콘 객체 로직 호출");
    }
/*
싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다
의존관계상 클라이언트가 구체 클래스에 의존한다
클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다
테스트하기 어렵다
내부 속성을 변경하거나 초기화 하기 어렵다
결론적으로 유연성이 떨어진다
안티패턴으로 불리기도 한다
 */


}
