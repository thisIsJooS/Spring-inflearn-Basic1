package joos.core;

import joos.core.discount.DiscountPolicy;
import joos.core.discount.FixDiscountPolicy;
import joos.core.discount.RateDiscountPolicy;
import joos.core.member.MemberRepository;
import joos.core.member.MemberService;
import joos.core.member.MemberServiceImpl;
import joos.core.member.MemoryMemberRepository;
import joos.core.order.Order;
import joos.core.order.OrderService;
import joos.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


}
