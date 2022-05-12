package com.bm.kakaopay.dto.accounts;

import com.bm.kakaopay.domain.Account;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 계좌 Dto
 */
@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountDto {

    private Long seq;

    private Long userId;

    private String userAccount;


    public AccountDto(Account account){
        copyProperties(account, this);
    }
}

