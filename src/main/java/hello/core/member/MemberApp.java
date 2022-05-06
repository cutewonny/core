package hello.core.member;

import hello.core.Order.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//이름, 타입


        Member member = new Member(1L, "워니", Grade.VIP);
        memberService.join(member);
        System.out.println("join = "+member.getName());

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = "+findMember.getName());

    }
}
