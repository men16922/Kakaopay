package com.bm.kakaopay.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 사용자 도메인
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "kakao_users")
public class KakaoUser {

    @Id
    @Column(name = "user_id", nullable = false)
    @Schema(name = "사용자 ID")
    private Long userId;

    @Column(name = "name", nullable = false)
    @Schema(name = "사용자 이름")
    private String name;

    @Column(name = "age", nullable = false)
    @Schema(name = "사용자 나이")
    private Integer age;

    @Column(name = "create_at", nullable = false)
    @Schema(name = "가입일")
    private LocalDate createAt;
}

