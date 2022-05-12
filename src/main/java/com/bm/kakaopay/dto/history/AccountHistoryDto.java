package com.bm.kakaopay.dto.history;

import com.bm.kakaopay.domain.AccountHistory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * 계좌내역 Dto
 */
@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountHistoryDto {

    private String userAccount;

    private String isTsmt;

    private Float amount;

    private LocalDate createAt;

    public AccountHistoryDto(AccountHistory accountHistory){
        copyProperties(accountHistory, this);

        this.isTsmt = accountHistory.getIs_tsmt();
    }
}

