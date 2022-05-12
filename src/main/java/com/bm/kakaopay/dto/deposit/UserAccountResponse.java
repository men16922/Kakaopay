package com.bm.kakaopay.dto.deposit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotNull;

/**
 * 사용자를 입력받아, 사용자의 계좌별 예치금을 출력 응답 Dto
 */
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccountResponse {

    @NotNull
    @Required
    private String userAccount;

    @NotNull
    @Required
    private Float amount;
}

