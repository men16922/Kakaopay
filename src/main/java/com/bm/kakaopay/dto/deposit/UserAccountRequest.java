package com.bm.kakaopay.dto.deposit;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 사용자를 입력받아, 사용자의 계좌별 예치금을 출력 요청 Dto
 */
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccountRequest {

    @NotNull
    @Required
    @NotBlank(message = "user_id is Mandatory")
    @Schema(description = "사용자ID", defaultValue = "8")
    private Long userId;
}

