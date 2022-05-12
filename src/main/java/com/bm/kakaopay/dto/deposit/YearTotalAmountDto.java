package com.bm.kakaopay.dto.deposit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * 년도를 입력받아, 해당년도의 예치금 총액을 출력 Dto
 */
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class YearTotalAmountDto {

    private float totalDeposit;
}

