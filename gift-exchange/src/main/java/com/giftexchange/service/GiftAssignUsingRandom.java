package com.giftexchange.service;

import com.giftexchange.model.Member;

import java.util.*;

public class GiftAssignUsingRandom implements GiftAssign {

    public Map<Member, Member> shuffle(List<Member> members) {

        if(members == null || members.isEmpty() || members.size() == 1) {
            System.out.println("Invalid list of members");
            return null;
        }

        Random random = new Random();
        List<Integer> receiverIdx = new ArrayList<>();
        Map<Member, Member> map = new HashMap<>();
        for (Member giver : members) {
            int x = random.nextInt(members.size());
            while (receiverIdx.contains(x) || !canAssign(x, members, giver)) {
                x = random.nextInt(members.size());
            }
            receiverIdx.add(x);
            map.put(giver, members.get(x));
        }
        map.forEach((m1, m2) -> System.out.println(m1.getName()+ " gives to "+ m2.getName()));
        return map;
    }

    @Override
    public void setLastAssigned(Map<String, String> lastAssigned) {

    }

    private boolean canAssign(int x, List<Member> members, Member member) {
        return !members.get(x).getId().equals(member.getId());
    }

}
