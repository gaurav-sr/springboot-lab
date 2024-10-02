package com.giftexchange.dao;

import com.giftexchange.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TestMemberDao {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testCreate() {
        MemberDao memberDao = new MemberDaoImpl(memberRepository);
        Member member = new Member();
        member.setName("GS");
        member = memberDao.insertMember(member);
        System.out.println(member.getId());
        Assertions.assertNotNull(member.getId());
    }

}
