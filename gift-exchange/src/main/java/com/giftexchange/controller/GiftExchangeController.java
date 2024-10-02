package com.giftexchange.controller;

import com.giftexchange.model.GiftList;
import com.giftexchange.model.Member;
import com.giftexchange.model.Members;
import com.giftexchange.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GiftExchangeController {

    private MemberService memberService;

    @Autowired
    public GiftExchangeController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Members> fetchMembers() {
        Members members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PostMapping(value = "/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        if(memberService.fetchMemberById(member.getId()) == null) {
            memberService.addMember(member);
            return new ResponseEntity<>(member, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberService.fetchMemberById(id);
        if(member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping(value = "/members/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Member updatedMember) {
        Member member = memberService.updateMemberById(id, updatedMember);
        if(member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Member> deleteMember(@PathVariable String id) {
        memberService.deleteMemberById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/gift_exchange")
    public ResponseEntity<List<GiftList>> giftExchange() {
        Map<Member, Member> map = memberService.prepareGiftExchangeList();
        List<GiftList> giftLists = new ArrayList<>();
        map.forEach((m1, m2) -> {
            GiftList giftList = GiftList.builder()
                    .member_id(m1.getId())
                    .recipient_member_id(m2.getId()).build();
            giftLists.add(giftList);
        });
        return new ResponseEntity<>(giftLists, HttpStatus.OK);
    }
}
