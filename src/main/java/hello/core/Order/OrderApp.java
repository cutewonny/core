package hello.core.Order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        appConfig.orderService()


        Long memberId = 1L;
        Member member = new Member(memberId, "member Name", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "product name", 10000);
        System.out.println("order: "+order);
    }
}
