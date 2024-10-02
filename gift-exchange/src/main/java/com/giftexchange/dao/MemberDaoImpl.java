package com.giftexchange.dao;

import com.giftexchange.entity.MemberEntity;
import com.giftexchange.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberDaoImpl implements MemberDao {

    final MemberRepository memberRepository;

    @Autowired
    public MemberDaoImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> fetchAllMembers() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<Member> members = new ArrayList<>();
        if(memberEntities != null) {
            memberEntities.forEach(memberEntity -> {
                Member member = Member.builder()
                        .id(memberEntity.getId())
                        .name(memberEntity.getName())
                        .build();
                members.add(member);
            });
        }
        return members;
    }

    public Member fetchMemberById(String id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElse(null);
        if (memberEntity != null) {
            return Member.builder().id(id).name(memberEntity.getName()).build();
        }
        return null;
    }

    public boolean insertMember(Member member) {
        if(member == null) {
            return false;
        }
        MemberEntity memberEntity = MemberEntity.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
        memberRepository.save(memberEntity);
        return true;
    }

    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }

    public void updateMember(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                                    .id(member.getId())
                                    .name(member.getName()).build();
        memberRepository.save(memberEntity);
    }
}
