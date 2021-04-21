package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    //메모리 초기화
    MemoryMemberRepository memberRepository= new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository= new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    //given
    //when
    //then
    //주석처리 = 블럭+ctrl+"/"  &  블럭+ctrl+shift+"/"
    //shift+F10 == 재실행
    @Test
    void member_join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /*중복회원(이름) 예외*/
    @Test
    void duplicateMembersE() {
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        Member member3 = new Member();
        member3.setName("spring3");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");


    }
  /*
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThrows(NullPointerException.class, () -> memberService.join(member2));


            try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다. 검증을 위해 오류 낸 부분입니다");
        }
        */

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}