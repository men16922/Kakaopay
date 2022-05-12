package com.bm.kakaopay.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

/**
 * 계좌 domain
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    @Schema(name = "seq")
    private Long seq;


    @Column(name = "user_id", nullable = false)
    @Schema(name = "사용자 ID")
    private Long userId;

    @Column(name = "user_account", nullable = false)
    @Schema(name = "계좌번호")
    private String userAccount;
}

