package com.bm.kakaopay.dto.history;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountHistoryAddRequest {

    @NotNull
    @Required
    @NotBlank(message = "user_account is Mandatory")
    private String userAccount;

    @NotNull  @Required
    @NotBlank(message = "is_tsmt is Mandatory")
    private String isTsmt;

    @NotNull @Required
    @NotBlank(message = "amount is Mandatory")
    private Float amount;

}

