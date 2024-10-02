package com.giftexchange.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String name;

    public boolean equals(Object o) {
        if ( o instanceof Member ) {
            return id.equals(((Member) o).id);
        }
        return false;
    }

    public int hashCode() {
        return id.hashCode();
    }
}
