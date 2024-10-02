package com.giftexchange.service;

import com.giftexchange.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGiftAssignUsingShuffle {

    GiftAssign giftAssign = new GiftAssignUsingShuffle();

    @Test
    public void testSingleMemberAssignment() {
        Member member1 = new Member("123", "GS0");
        List<Member> members = List.of(member1);
        Map<Member, Member> giftAssignment = giftAssign.shuffle(members);
        Assertions.assertNull(giftAssignment);
    }

    @Test
    public void testTwoMemberAssignment() {
        Member member1 = new Member("123", "GS0");
        Member member2 = new Member("124", "GS1");
        List<Member> members = List.of(member1, member2);
        Map<Member, Member> giftAssignment = giftAssign.shuffle(members);
        Assertions.assertNotNull(giftAssignment);
        Assertions.assertEquals(2, giftAssignment.size());
    }

    @Test
    public void test3MembersAssignment() {
        Member member1 = new Member("121", "GS1");
        Member member2 = new Member("122", "GS2");
        Member member3 = new Member("123", "GS3");
        List<Member> members = List.of(member1, member2, member3);
        Map<Member, Member> giftAssignment = giftAssign.shuffle(members);
        Assertions.assertNotNull(giftAssignment);
        Assertions.assertEquals(3, giftAssignment.size());
    }

    @Test
    public void testOddMembersAssignment() {
        Member member1 = new Member("121", "GS1");
        Member member2 = new Member("122", "GS2");
        Member member3 = new Member("123", "GS3");
        Member member4 = new Member("124", "GS4");
        Member member5 = new Member("125", "GS5");
        List<Member> members = List.of(member1, member2, member3, member4, member5);
        Map<Member, Member> giftAssignment = giftAssign.shuffle(members);
        Assertions.assertNotNull(giftAssignment);
        Assertions.assertEquals(5, giftAssignment.size());
    }

    @Test
    public void testAssignment() {
        Map<String,String> map = new HashMap<>();
        map.put("123:456","01/01/2021");
        map.put("789:887","01/01/2021");
        giftAssign.setLastAssigned(map);
        Member member1 = new Member("123", "GS0");
        Member member2 = new Member("456", "GS1");
        Member member3 = new Member("789", "GS2");
        Member member4 = new Member("887", "GS3");
        Member member5 = new Member("888", "GS4");
        Member member6 = new Member("999", "GS5");
        List<Member> members = List.of(member1, member2, member3, member4, member5, member6);
        Map<Member, Member> giftList = giftAssign.shuffle(members);
        Assertions.assertNotNull(giftList);
        Assertions.assertEquals(giftList.size(), 6);
    }

}
