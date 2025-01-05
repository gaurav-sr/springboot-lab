package com.sblab.sbdocker;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class WalletController {

    @GetMapping(value = "/now", produces = MediaType.TEXT_PLAIN_VALUE)
    public String showNowTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toString();
    }

    @GetMapping(value="/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Wallet> showAllWallets() {
        return Arrays.asList(new Wallet("Gpay"), new Wallet("ApplePay"));
    }

}
