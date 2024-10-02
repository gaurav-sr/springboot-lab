package com.giftexchange.service;

import com.giftexchange.model.Member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class GiftAssignUsingRandom implements GiftAssign {

    private Map<String, String> lastAssigned = new HashMap<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Map<Member, Member> shuffle(List<Member> members) {

        if (members == null || members.isEmpty() || members.size() == 1) {
            System.out.println("Invalid list of members");
            return null;
        }
        Map<Member, Member> map = new HashMap<>();
        if(members.size() == 2) {
            map.put(members.get(0), members.get(1));
            map.put(members.get(1), members.get(0));
            return map;
        }

        Random random = new Random();
        List<Integer> receiverIdx = new ArrayList<>();
        for (Member giver : members) {
            int x = random.nextInt(members.size());
            while (receiverIdx.contains(x) || cannotAssign(x, members, giver, members.get(x))) {
                x = random.nextInt(members.size());
            }
            receiverIdx.add(x);
            map.put(giver, members.get(x));
        }
        map.forEach((m1, m2) -> System.out.println(m1.getName() + " gives to " + m2.getName()));
        return map;
    }

    @Override
    public void setLastAssigned(Map<String, String> lastAssigned) {
        this.lastAssigned = lastAssigned;
    }

    private boolean cannotAssign(int x, List<Member> members, Member giver, Member taker) {
        return sameMember(x, members, giver) && lastAssigned(3, giver, taker);
    }

    private boolean sameMember(int x, List<Member> members, Member giver) {
        return members.get(x).getId().equals(giver.getId());
    }

    private boolean lastAssigned(int year, Member giver, Member taker) {
        String key = giver.getId() + ":" + taker.getId();
        if (lastAssigned.containsKey(key)) {
            System.out.println("Giver Taker Match found...");
            String lastAssignedDate = lastAssigned.get(key);
            try {
                Date assignedDate = dateFormat.parse(lastAssignedDate);
                LocalDate assignedLocalDate = LocalDate.ofInstant(assignedDate.toInstant(), ZoneId.systemDefault());
                LocalDate currentLocalDate = LocalDate.now();
                Period period = Period.between(assignedLocalDate, currentLocalDate);
                int diffYears = Math.abs(period.getYears());
                return diffYears > year;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }



}
