package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{//클라이언트에서 주문을 생성함 return 주문결과

    private final MemberRepository memberRepository = new MemoryMemberRepository();//회원DB
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//등급별 할인금액 알려줌

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {//구현
        Member member = memberRepository.findById(memberId);//멤버 찾는다
        int discountPrice = discountPolicy.discount(member, itemPrice);//멤버에 속한 할인을 가져온다 단일분리 원칙 잘 지킴

        return new Order(memberId, itemName, itemPrice, discountPrice);//학인금액까지 전달
    }
}
