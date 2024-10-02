package com.giftexchange.dao;

import com.giftexchange.entity.MemberEntity;
import com.giftexchange.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberDao {
    List<Member> fetchAllMembers();
    Member fetchMemberById(String id);
    Member insertMember(Member member);
    void deleteMember(String id);
    void updateMember(Member member);
}
