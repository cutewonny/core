package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;//무조건 천원 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){//등급에 따라 enum은 == 이다
            return discountFixAmount;
        }else{
            return 0;
        }

    }
}
