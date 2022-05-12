package com.bm.kakaopay.dto.accounts;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 계좌 추가 요청 Dto
 */
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountAddRequest {

    @NotNull  @Required
    @NotBlank(message = "user_id is Mandatory")
    @Schema(description = "사용자ID", defaultValue = "8")
    private Long userId;

    @NotNull @Required
    @NotBlank(message = "user_account is Mandatory")
    @Schema(description = "사용자계좌", defaultValue = "1000-66")
    private String userAccount;

}

