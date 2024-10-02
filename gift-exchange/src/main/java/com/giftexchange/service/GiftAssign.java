package com.giftexchange.service;

import com.giftexchange.model.Member;

import java.util.List;
import java.util.Map;

public interface GiftAssign {

    Map<Member, Member> shuffle(List<Member> members);
    void setLastAssigned(Map<String, String> lastAssigned);

}
