package joos.core.discount;

import joos.core.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
