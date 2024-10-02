package com.giftexchange.service;

import com.giftexchange.model.Member;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class GiftAssignUsingShuffle implements GiftAssign {

    @Setter
    private Map<String, String> lastAssigned = new HashMap<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public Map<Member, Member> shuffle(List<Member> members) {

        if(members == null || members.isEmpty() || members.size() == 1) {
            System.out.println("Invalid list of members");
            return null;
        }
        Map<Member, Member> map = new HashMap<>();
        if(members.size() == 2) {
            map.put(members.get(0), members.get(1));
            map.put(members.get(1), members.get(0));
            return map;
        }

        List<Integer> range = IntStream.rangeClosed(0, members.size()-1)
                .boxed().toList();
        ArrayList<Integer> shuffledIdx = new ArrayList<>();
        shuffledIdx.addAll(range);
        Collections.shuffle(shuffledIdx);

        List<Integer> receiverIdx = new ArrayList<>();
        for(int i = 0; i < shuffledIdx.size(); i++) {
            if(i == shuffledIdx.size() - 1) {
                map.put(members.get(shuffledIdx.get(i)), members.get(shuffledIdx.getFirst()));

            } else {
                map.put(members.get(shuffledIdx.get(i)), members.get(shuffledIdx.get(i + 1)));
            }
        }
//        for(Integer idx : shuffledIdx) {
//            Member member = members.get(idx);
//            System.out.println("Finding pair for "+member.getId());
//            for(Integer idx2 : shuffledIdx) {
//                if(idx2.equals(idx) || receiverIdx.contains(idx2)) {
//                    continue;
//                }
//                Member member2 = members.get(idx2);
//                String key = member.getId()+":"+member2.getId();
//                if(lastAssigned.containsKey(key)) {
//                    System.out.println("Key Match found...");
//                    String val = lastAssigned.get(key);
//                    try {
//                        Date assignedDate = dateFormat.parse(val);
//                        LocalDate assignedLocalDate = LocalDate.ofInstant(assignedDate.toInstant(), ZoneId.systemDefault());
//                        LocalDate currentLocalDate = LocalDate.now();
//                        Period period = Period.between(assignedLocalDate, currentLocalDate);
//                        int diffYears = Math.abs(period.getYears());
//                        if(diffYears > 3) {
//                            map.put(member, member2);
//                            receiverIdx.add(idx2);
//                            break;
//                        } else {
//                            System.out.println("Found match with less than 3 years");
//                        }
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                } else {
//                    System.out.println("Pair Found "+member2.getId());
//                    map.put(member, member2);
//                    receiverIdx.add(idx2);
//                    break;
//                }
//            }
//        }
        map.forEach((m1, m2) -> System.out.println(m1.getName()+ " gives to "+ m2.getName()));
        return map;
    }

}
