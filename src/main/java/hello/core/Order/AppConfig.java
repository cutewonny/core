package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//ctrl n

/*
@ Configuration 사용 이유
@Bean 을 사용해도 스프링 빈으로 등록되지만, 싱글톤 보장 안함 : 객체가 서로 다름
memberRepository()처럼 의존관계 주입이 필요해서 메서드를 직접 호출할때 싱글톤을 보장 안함
그래서 Configuration을 사용하면 싱글톤 보장함
 */
@Configuration
public class AppConfig {
    /*
    여기 있는 애들을 SingletonServcie처럼 싱글톤으로 만들어버리면
    싱글톤이 적용되겠네? 아니다.
    스프링컨테이너가 객체를 알아서 싱글톤으로 생성해준다.
    있는 객체를 재활용한다
    */

    //AppConfig: 생성한 객체 인스턴스의 참조를 -> 생성자를 통해서 -> 주입
    @Bean
    public MemberService memberService(){
        System.out.println("1");
        return new MemberServiceImpl(memberRepository());//객체 선언 -> 생성자 // 한번 호출
        //생성자 주입
    }
    @Bean
    public MemberRepository memberRepository(){ // ctrl Alt M, Extract Method
        System.out.println("2");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("3");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
        //생성자 주입
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("4");
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    //@Configuration 설정정보
    //@Bean 스프링 컨테이너에 등록된다
}
