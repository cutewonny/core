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

@Configuration
public class AppConfig {
    //AppConfig: 생성한 객체 인스턴스의 참조를 -> 생성자를 통해서 -> 주입
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());//객체 선언 -> 생성자
        //생성자 주입
    }
    @Bean
    public MemberRepository memberRepository(){ // ctrl Alt M, Extract Method
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
        //생성자 주입
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    //@Configuration 설정정보
    //@Bean 스프링 컨테이너에 등록된다
}
