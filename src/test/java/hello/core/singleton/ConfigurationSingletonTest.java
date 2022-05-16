package hello.core.singleton;

import hello.core.Order.AppConfig;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("AppConfig에서 memberRepository가 각각호출되는데 같은 인스턴스다")
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderService orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);


        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = ((OrderServiceImpl) orderService).getMemberRepository();

        System.out.println("memberService -> memberRepository1>> "+memberRepository1);
        System.out.println("orderService -> memberRepository2>> "+memberRepository2);
        System.out.println(" 원본 -> memberRepository>> "+memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(((OrderServiceImpl) orderService).getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);//바이트 코드 조작 라이브러리-> 다른 클래스 만듦

        System.out.println("bean>> "+bean.getClass());
        //bean>> class hello.core.Order.AppConfig    $$EnhancerBySpringCGLIB$$4389a741

    }
}
