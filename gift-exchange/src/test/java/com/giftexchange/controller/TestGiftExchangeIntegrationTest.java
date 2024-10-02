package com.giftexchange.controller;

import com.giftexchange.GiftExchange;
import com.giftexchange.model.GiftList;
import com.giftexchange.model.Member;
import com.giftexchange.model.Members;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GiftExchange.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GiftExchangeIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final static String HOST = "http://localhost:";

    @Test
    public void testGetAllMembers() {
        ResponseEntity<Members> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members", Members.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(0, Objects.requireNonNull(responseEntity.getBody()).getMembers().size());
    }

    @Test
    public void testAddMember() {
        Member member = Member.builder().id("123").name("GS").build();
        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member, Member.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        ResponseEntity<Members> getResponseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members", Members.class);
        Assertions.assertEquals(HttpStatus.OK, getResponseEntity.getStatusCode());
        Assertions.assertEquals(1, Objects.requireNonNull(getResponseEntity.getBody()).getMembers().size());
    }

    @Test
    public void addDuplicateMember_shouldReturnAlreadyExist() {
        Member member = Member.builder().id("123").name("GS").build();
        ResponseEntity<Member> responseEntity = restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member, Member.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        responseEntity = restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member, Member.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetMembers() {
        Member member_1 = Member.builder().id("123").name("GS1").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_1, Member.class);
        Member member_2 = Member.builder().id("124").name("GS2").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_2, Member.class);
        Member member_3 = Member.builder().id("125").name("GS3").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_3, Member.class);


        ResponseEntity<Members> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members", Members.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).getMembers().size());
    }

    @Test
    public void testGetMemberId() {
        Member member_1 = Member.builder().id("123").name("GS1").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_1, Member.class);
        ResponseEntity<Member> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members/123", Member.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("GS1", responseEntity.getBody().getName());
    }

    @Test
    public void testGetMemberId_NotFound() {
        ResponseEntity<Member> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members/123", Member.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteMember() {

        Member member_1 = Member.builder().id("123").name("GS1").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_1, Member.class);

        restTemplate.delete(HOST + port + "/gift-exchange/members/123");

        ResponseEntity<Member> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/members/123", Member.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGiftExchange() {
        // given
        Member member_1 = Member.builder().id("123").name("GS1").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_1, Member.class);
        Member member_2 = Member.builder().id("124").name("GS2").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_2, Member.class);
        Member member_3 = Member.builder().id("125").name("GS3").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_3, Member.class);
        Member member_4 = Member.builder().id("126").name("GS4").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_4, Member.class);
        Member member_5 = Member.builder().id("127").name("GS5").build();
        restTemplate.postForEntity(HOST + port + "/gift-exchange/members", member_5, Member.class);

        //when
        ResponseEntity<GiftList[]> responseEntity = restTemplate.getForEntity(HOST + port + "/gift-exchange/gift_exchange", GiftList[].class);

        //Then
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}
