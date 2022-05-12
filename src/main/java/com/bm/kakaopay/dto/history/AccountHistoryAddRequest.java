package com.bm.kakaopay.dto.history;

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
 * 계좌내역 추가 요청 Dto
 */
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountHistoryAddRequest {

    @NotNull
    @Required
    @NotBlank(message = "user_account is Mandatory")
    @Schema(description = "사용자계좌", defaultValue = "1000-44")
    private String userAccount;

    @NotNull  @Required
    @NotBlank(message = "is_tsmt is Mandatory")
    @Schema(description = "입/출금 : Y/N", defaultValue = "Y")
    private String isTsmt;

    @NotNull @Required
    @NotBlank(message = "amount is Mandatory")
    @Schema(description = "금액", defaultValue = "8000")
    private Float amount;

}

