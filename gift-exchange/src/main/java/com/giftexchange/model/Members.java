package com.giftexchange.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Members {

    private List<Member> members;
    public Members() {
        members = new ArrayList<>();
    }
}
