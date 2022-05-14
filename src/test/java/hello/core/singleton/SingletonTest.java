package hello.core.singleton;

import hello.core.Order.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다름을 확인
        System.out.println("memberService1=" + memberService1);
        System.out.println("memberService2=" + memberService2);
        /*
        memberService1=hello.core.member.MemberServiceImpl@66d18979
        memberService2=hello.core.member.MemberServiceImpl@bccb269
        jvm에 객체가 생성되겠지....객체를 생성하는 건 효율적이지 못하다
         */

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {

        //private로 생성자를 막아두었다. 컴파일 오류가 생긴다
//        new SingletonService();
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println("sington1>>> " + singletonService1);
        System.out.println("sington2>>> " + singletonService2);
        /*
        sington1>>> hello.core.singleton.SingletonService@63475ace
        sington2>>> hello.core.singleton.SingletonService@63475ace
         */
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //same : = = : 주소값을 비교함
        //equal : 값 자체를 비교함
        singletonService1.logic();
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//        MemberService memberService1 = appConfig.memberService();
//        MemberService memberService2 = appConfig.memberService();

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조 값이 다름을 확인
        System.out.println("memberService1=" + memberService1);
        System.out.println("memberService2=" + memberService2);
        /*
        memberService1=hello.core.member.MemberServiceImpl@66d18979
        memberService2=hello.core.member.MemberServiceImpl@bccb269
        jvm에 객체가 생성되겠지....객체를 생성하는 건 효율적이지 못하다
         */
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}
