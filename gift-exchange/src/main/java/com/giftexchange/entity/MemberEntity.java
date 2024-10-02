package com.giftexchange.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberEntity {

    @Id
    private String id;

    @Column(name="name", nullable = false)
    private String name;

}
