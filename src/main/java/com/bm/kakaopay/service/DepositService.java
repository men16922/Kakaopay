package com.bm.kakaopay.service;

import com.bm.kakaopay.domain.Account;
import com.bm.kakaopay.domain.AccountHistory;
import com.bm.kakaopay.dto.deposit.AgeGroupAmountDto;
import com.bm.kakaopay.dto.deposit.TimeUserDescDto;
import com.bm.kakaopay.dto.deposit.YearTotalAmountDto;
import com.bm.kakaopay.dto.users.UserAccountRequest;
import com.bm.kakaopay.dto.users.UserAccountResponse;
import com.bm.kakaopay.exception.AccountFailException;
import com.bm.kakaopay.exception.AccountHistoryFailException;
import com.bm.kakaopay.exception.UserFailException;
import com.bm.kakaopay.repository.AccountHistoryRepository;
import com.bm.kakaopay.repository.AccountRepository;
import com.bm.kakaopay.repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service

public class DepositService {

    private final KakaoUserRepository kakaoUserRepository;

    private final AccountRepository accountRepository;

    private final AccountHistoryRepository accountHistoryRepository;

    @Transactional(readOnly = true)
    public List<UserAccountResponse> userAccounts(UserAccountRequest userAccountRequest) {

        kakaoUserRepository.findById(userAccountRequest.getUserId()).orElseThrow(() -> new UserFailException("user_id 조회에 실패하였습니다."));

        List<Account> accountList = accountRepository.findAllByUserId(userAccountRequest.getUserId());
        if(accountList == null) {
            throw new AccountFailException("user_id의 계좌가 존재하지 않습니다.");
        }

        List<UserAccountResponse> userAccountResponseList = new ArrayList<>();

        /**
         * 사용자 계좌 리스트
         */
        for(Account account : accountList) {
            List<AccountHistory> accountHistoryList = accountHistoryRepository.findAllByUserAccount(account.getUserAccount());

            if(accountHistoryList == null) {
                throw new AccountHistoryFailException("user_id의 계좌내역이 존재하지 않습니다.");
            }

            /**
             * 사용자 계좌별 계좌내역
             */
            float deposit = 0f;

            for(AccountHistory accountHistory : accountHistoryList) {
                if(StringUtils.equals(accountHistory.getIs_tsmt(), "Y")) {
                    deposit += accountHistory.getAmount();
                } else {
                    deposit -= accountHistory.getAmount();
                }
            }

            userAccountResponseList.add(new UserAccountResponse(account.getUserAccount(), deposit));

        }

        return userAccountResponseList;
    }

    @Transactional(readOnly = true)
    public List<AgeGroupAmountDto> ageGroupAmount() {
        List<Object[]> ageGroupAmountList = accountHistoryRepository.findAgeGroupAmount();

        List<AgeGroupAmountDto> ageGroupAmountDtoList = new ArrayList<>();

        for(Object[] ageGroupAmount : ageGroupAmountList) {
            log.info("AGE-GROUP -{}", ageGroupAmount);
            ageGroupAmountDtoList.add(new AgeGroupAmountDto(Integer.parseInt(ageGroupAmount[0].toString()), Float.parseFloat(ageGroupAmount[1].toString())));
        }

        return ageGroupAmountDtoList;
    }

    @Transactional(readOnly = true)
    public YearTotalAmountDto yearTotalAmount(String year) {

        float totalDeposit = accountHistoryRepository.findYearTotalAmount(year).orElseThrow(() -> new AccountHistoryFailException("예치금 조회에 실패하였습니다."));

        return YearTotalAmountDto.builder()
                .totalDeposit(totalDeposit)
                .build();
    }

    @Transactional(readOnly = true)
    public List<TimeUserDescDto> timeUserDesc(String startYear, String endYear) {

        List<Object[]> timeUserDescList = accountHistoryRepository.timeUserDesc(startYear, endYear);

        List<TimeUserDescDto> timeUserDescDtoList = new ArrayList<>();

        for(Object[] timeUserDesc : timeUserDescList) {
            timeUserDescDtoList.add(new TimeUserDescDto(timeUserDesc[0].toString(), timeUserDesc[1].toString(), Float.parseFloat(timeUserDesc[2].toString())));
        }

        return timeUserDescDtoList;
    }

}

