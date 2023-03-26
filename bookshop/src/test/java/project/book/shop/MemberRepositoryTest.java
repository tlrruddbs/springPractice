package project.book.shop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import project.book.shop.entity.Member;
import project.book.shop.repository.MemberRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("member test")
    @Rollback(value = true)
    public void memberTest(){
        Member member = new Member();//= new Member("A", new Address("서울", "거리", "13529"));
        member.setUserName("memberA");
        long saveId = memberRepository.save(member).getId();

        Member findMember = memberRepository.findById(saveId).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}