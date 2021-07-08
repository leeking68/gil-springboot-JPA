package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author : Gilho Lee
 */
@Service
@Transactional(readOnly = true) // 이게 디폴트
@RequiredArgsConstructor
public class MemberService {

    private final MemberRespository memberRespository;



    /**
     *
     * 회원가입
     *
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicdateMember(member); //검증
        memberRespository.save(member);
        return member.getId();
    }

    private void validateDuplicdateMember(Member member) {
        //Exception
         List<Member> findMembers = memberRespository.findByName(member.getName());


         if(!findMembers.isEmpty()) {
             throw new IllegalStateException(("이미 존재하는 회원입니다."));
        }

    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRespository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRespository.findOne(memberId);
    }
}
