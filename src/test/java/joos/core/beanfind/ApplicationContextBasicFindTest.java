package joos.core.beanfind;

import joos.core.AppConfig;
import joos.core.member.MemberService;
import joos.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void findBeanByName() throws Exception{
        //given
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    public void findBeanByType() throws Exception{
        //given
        MemberService memberService = ac.getBean(MemberService.class);

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    public void findBeanByName2() throws Exception{
        //given
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);

        //then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    public void findBeanByNameX() throws Exception{
        //given
//        MemberService xxx = ac.getBean("xxxxx", MemberService.class);   // NoSuchBeanDefinitionException

        //then
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }


}
