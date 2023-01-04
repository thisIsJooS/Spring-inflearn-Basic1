package joos.core.order;

import joos.core.AppConfig;
import joos.core.member.Grade;
import joos.core.member.Member;
import joos.core.member.MemberService;
import joos.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        /*
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = applicationContext.getBean("memberService", MemberService.class);
        orderService = applicationContext.getBean("orderService", OrderService.class);
    }

    @Test
    public void createOrder() throws Exception{
        //given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        
        //when
        memberService.join(member);
        Order order =  orderService.createOrder(memberId, "itemA", 10000);
        
        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
    
}
