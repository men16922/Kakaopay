package com.bm.kakaopay.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 계좌내역 도메인
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts_history")
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    @Schema(name = "seq")
    private Long seq;

    @Column(name = "user_account", nullable = false)
    @Schema(name = "계좌번호")
    private String userAccount;

    @Column(name = "is_tsmt", nullable = false)
    @Schema(name = "입금여부")
    private String is_tsmt;

    @Column(name = "amount", nullable = false)
    @Schema(name = "입금액")
    private Float amount;

    @Column(name = "create_at", nullable = false)
    @Schema(name = "가입일")
    private LocalDate createAt;

}

