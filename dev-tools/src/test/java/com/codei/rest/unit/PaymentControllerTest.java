package com.codei.rest.unit;

import com.codei.rest.controller.PaymentController;
import com.codei.rest.payments.Constants;
import com.codei.rest.payments.PaymentRequest;
import com.codei.rest.payments.PaymentResponse;
import com.codei.rest.payments.PaymentService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * This is unit test of controller class using mock objects
 */
@WebMvcTest(PaymentController.class)
@RunWith(SpringRunner.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void testHello() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = this.mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.content().string(Constants.HELLO_GREETING));
    }

    @Test
    public void test() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setCardId("3124243123456789");
        BDDMockito
                .given(paymentService.processPayment(Matchers.eq(paymentRequest)))
                .willReturn(new PaymentResponse("SomeId", "SUCCESS"));

        Gson gson = new Gson();
        String paymentRequestJson = gson.toJson(paymentRequest);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .post("/payments")
                .content(paymentRequestJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions resultActions = mockMvc
                .perform( mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        String res = resultActions.andReturn().getResponse().getContentAsString();
        Assert.assertNotNull(res);
        Assert.assertTrue(res.length() > 0);
        PaymentResponse paymentResponse = gson.fromJson(res, PaymentResponse.class);
        Assert.assertEquals(Constants.SUCCESS_MESSAGE, paymentResponse.getMessage());
        Assert.assertEquals("SomeId", paymentResponse.getId());
    }

}
