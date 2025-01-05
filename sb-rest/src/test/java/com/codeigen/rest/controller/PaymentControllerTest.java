package com.codeigen.rest.controller;

import com.codeigen.rest.payments.Constants;
import com.codeigen.rest.payments.PaymentRequest;
import com.codeigen.rest.payments.PaymentResponse;
import com.codeigen.rest.payments.PaymentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is unit test of controller class using mock objects
 */
@WebMvcTest(PaymentController.class)
@ExtendWith(SpringExtension.class)

public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    //@Test
    public void testHello() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.TEXT_PLAIN);
        ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string(Constants.HELLO_MESSAGE));
    }

    //@Test
    public void test() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("3124243123456789");

        BDDMockito
                .given(paymentService.processPayment(paymentRequest))
                .willReturn(PaymentResponse.builder().cardId("SomeId").message("SUCCESS").build());

        Gson gson = new Gson();
        String paymentRequestJson = gson.toJson(paymentRequest);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/payment")
                .content(paymentRequestJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = mockMvc
                .perform( mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        String res = resultActions.andReturn().getResponse().getContentAsString();
        assertNotNull(res);
        assertFalse(res.isEmpty());
        PaymentResponse paymentResponse = gson.fromJson(res, PaymentResponse.class);
        assertEquals("SUCCESS", paymentResponse.getMessage());
        assertEquals("SomeId", paymentResponse.getCardId());
    }

}
