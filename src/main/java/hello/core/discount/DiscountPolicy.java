package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /*
    * @return 할인 대상 금액
     */
    int discount(Member member, int price);//f2 오류난 곳으로 간다
}
