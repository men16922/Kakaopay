package com.bm.kakaopay.dto.deposit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * 기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력 Dto
 */
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TimeUserDescDto {

    private String userId;

    private String name;

    private float deposit;
}

