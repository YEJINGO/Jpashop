//package jpabook.jpashop.service;
//
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @DisplayName("회원가입")
//    public void join() throws Exception {
//        Member member = new Member();
//        member.setName("Go");
//
//        Long saveId = memberService.join(member);
//
////        Assertions.assertThat(memberRepository.findOne(saveId)).isEqualTo(member.getId());
//        assertEquals(member, memberRepository.findOne(saveId));
//    }
//
//    @Test
//    @DisplayName("중복 회원 예외")
//    public void exception() throws Exception {
//        //given
//        Member member1 = new Member();
//        member1.setName("Go");
//
//        Member member2 = new Member();
//        member2.setName("Go");
//        //when
//        memberService.join(member1);
//        memberService.join(member2);
//        //then
//        fail("예외 발생 ");
//    }
//}