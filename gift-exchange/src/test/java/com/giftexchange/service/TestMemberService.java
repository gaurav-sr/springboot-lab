package com.giftexchange.service;

import com.giftexchange.dao.MemberDao;
import com.giftexchange.dao.MemberDaoImpl;
import com.giftexchange.dao.MemberRepository;
import com.giftexchange.entity.MemberEntity;
import com.giftexchange.model.Member;
import com.giftexchange.model.Members;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestMemberService {

    @InjectMocks
    MemberService memberService;
    @Mock
    MemberRepository memberRepository;

    @Test
    public void fetchAllMembers_NoMembers_EmptyList(){

        Mockito.when(memberRepository.findAll()).thenReturn(new ArrayList<>());
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService(memberDao);
        Members members = memberService.getAllMembers();

        Assertions.assertTrue(members.getMembers().isEmpty());
    }

    @Test
    public void fetchAllMembers_NullMembers_EmptyList(){

        Mockito.when(memberRepository.findAll()).thenReturn(null);
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService(memberDao);
        Members members = memberService.getAllMembers();

        Assertions.assertTrue(members.getMembers().isEmpty());
    }

    @Test
    public void fetchAllMembers_OneMember_ListHasOneMember(){
        MemberEntity memberEntity = MemberEntity.builder().id(123l).name("GS").build();
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(memberEntity));
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService(memberDao);
        Members members = memberService.getAllMembers();

        Assertions.assertEquals(1, members.getMembers().size());
    }

    @Test
    public void fetchAllMembers_ManyMembers_ListHasAllMembers(){
        MemberEntity memberEntity_1 = MemberEntity.builder().id(121l).name("GS1").build();
        MemberEntity memberEntity_2 = MemberEntity.builder().id(122l).name("GS2").build();
        MemberEntity memberEntity_3 = MemberEntity.builder().id(123l).name("GS3").build();
        MemberEntity memberEntity_4 = MemberEntity.builder().id(124l).name("GS4").build();
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(memberEntity_1, memberEntity_2, memberEntity_3, memberEntity_4));
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService(memberDao);
        Members members = memberService.getAllMembers();

        Assertions.assertEquals(4, members.getMembers().size());
    }

    @Test
    public void fetchMemberById_OneMember_ReturnOneMember() {
        MemberEntity memberEntity = MemberEntity.builder().id(123l).name("GS").build();
        Mockito.when(memberRepository.findById("123")).thenReturn(Optional.ofNullable(memberEntity));
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService( memberDao);
        Member member = memberService.fetchMemberById("123");
        Assertions.assertNotNull(member);
    }

    @Test
    public void fetchMemberById_MemberNotFound_ReturnNull() {
        Mockito.when(memberRepository.findById("123")).thenReturn(Optional.empty());
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService( memberDao);
        Member member = memberService.fetchMemberById("123");
        Assertions.assertNull(member);
    }

    @Test
    public void fetchMemberById_InvalidMember_ReturnNull() {
        Mockito.when(memberRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        MemberDao memberDao = new MemberDaoImpl(memberRepository);

        memberService = new MemberService( memberDao);
        Member member = memberService.fetchMemberById("121");
        Assertions.assertNull(member);
    }

    @Test
    public void addMember_validMember_insertSuccess() {
        MemberEntity memberEntity = MemberEntity.builder().name("GS").build();
        MemberEntity createdMemberEntity = MemberEntity.builder().id(1L).name("GS").build();
        Mockito.when(memberRepository.save(memberEntity)).thenReturn(createdMemberEntity);
        MemberDao memberDao = new MemberDaoImpl(memberRepository);
        memberService = new MemberService( memberDao);
        Member member = Member.builder().name("GS").build();
        memberService.addMember(member);
    }

    @Test
    public void addMember_nullMember_insertFail() {
        MemberDao memberDao = new MemberDaoImpl(memberRepository);
        memberService = new MemberService( memberDao);
        Assertions.assertNull(memberService.addMember(null));
    }
}
