package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRespository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRespository memberRespository;
    @Autowired
    EntityManager em;
    @Test
   // @Rollback(false) // 한번보고싶어
    public void 회원가입() throws Exception {

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush(); 디비에 강제로 쿼리 날리기
        assertEquals(member, memberRespository.findOne(saveId));
        //영속성안에서 같은 ID면 같은 영속성에서 똑같은 애가 관리, 딱 하나만 관리
    }

//    @Test(expected = IllegalStateException.class)
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim2");

        //when
        memberService.join(member1);

        try {
            memberService.join(member2); // 예외 발생해야함

        } catch (IllegalStateException e) {
            return;
        }

        memberService.join(member2);

        //then
        //fail("예외가 발생한다.")
    }

}