package com.giftexchange.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="member")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

}
