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

        List<Integer> range = IntStream.rangeClosed(0, members.size()-1)
                .boxed().toList();
        ArrayList<Integer> shuffledIdx = new ArrayList<>();
        shuffledIdx.addAll(range);
        Collections.shuffle(shuffledIdx);
        System.out.println("Shuffled Index "+shuffledIdx);
        List<Integer> receiverIdx = new ArrayList<>();
        for(int giverIdx=0 ; giverIdx < shuffledIdx.size() ;giverIdx++ ) {
            Member giver = members.get(shuffledIdx.get(giverIdx));
            System.out.println("Finding pair for "+giver.getName());
            int takerIdx = 0;
            for(takerIdx = giverIdx + 1; takerIdx < shuffledIdx.size(); takerIdx++) {
                if(receiverIdx.contains(shuffledIdx.get(takerIdx))) {
                    continue;
                }
                Member taker = members.get(shuffledIdx.get(takerIdx));
                if(lastAssignedInLastGivenYears(3, giver, taker)) {
                    continue;
                }
                System.out.println("Pair Found "+taker.getId());
                map.put(giver, taker);
                receiverIdx.add(shuffledIdx.get(takerIdx));
                break;
            }
            if(takerIdx == shuffledIdx.size()) {
                for(takerIdx = 0; takerIdx < giverIdx; takerIdx++) {
                    if(receiverIdx.contains(shuffledIdx.get(takerIdx))) {
                        continue;
                    }
                    Member taker = members.get(shuffledIdx.get(takerIdx));
                    if(lastAssignedInLastGivenYears(3, giver, taker)) {
                        continue;
                    }
                    map.put(giver, taker);
                    receiverIdx.add(shuffledIdx.get(takerIdx));
                }
            }
        }
        map.forEach((m1, m2) -> System.out.println(m1.getName()+ " gives to "+ m2.getName()));
        return map;
    }

    private boolean lastAssignedInLastGivenYears(int year, Member giver, Member taker) {
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
