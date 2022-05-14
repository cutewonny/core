package hello.core;

import hello.core.Order.AppConfig;
import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();//서비스 불러옴
//        OrderService orderService = new OrderServiceImpl();;//주문 서비스 불러옴
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        Long memberId =1L;
        Member wonny = new Member(memberId, "wonny", Grade.VIP);//ctrl alt v
        memberService.join(wonny);//메모리에 저장

        Order order = orderService.createOrder(memberId, "product Name", 10000);//주문 생성
        System.out.println("order = "+order);
        System.out.println("order = "+order.calculatePrice());


    }
}
