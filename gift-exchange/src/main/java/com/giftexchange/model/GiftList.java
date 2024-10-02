package com.giftexchange.model;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
public class GiftList {
    private String member_id;
    private String recipient_member_id;
}
