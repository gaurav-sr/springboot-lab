package com.giftexchange.service;

import com.giftexchange.dao.MemberDao;
import com.giftexchange.model.Member;
import com.giftexchange.model.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    private MemberDao memberDao;

    @Autowired
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Members getAllMembers() {
        Members members = new Members();
        List<Member> memberList = memberDao.fetchAllMembers();
        members.setMembers(memberList);
        return members;
    }

    public boolean addMember(Member member) {
        return memberDao.insertMember(member);
    }

    public Member fetchMemberById(String id) {
        return memberDao.fetchMemberById(id);
    }

    public Member updateMemberById(String id, Member updatedMember) {
        Member member =  memberDao.fetchMemberById(id);
        if (member != null) {
            updatedMember.setId(id);
            memberDao.updateMember(updatedMember);
            return updatedMember;
        } else {
            return null;
        }

    }

    public void deleteMemberById(String id) {
        memberDao.deleteMember(id);
    }

    public Map<Member, Member> prepareGiftExchangeList() {
        Members members = getAllMembers();
        GiftAssign giftShuffle = new GiftAssignUsingShuffle();
        return giftShuffle.shuffle(members.getMembers());
    }
}
