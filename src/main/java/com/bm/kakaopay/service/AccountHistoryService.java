package com.bm.kakaopay.service;

import com.bm.kakaopay.domain.AccountHistory;
import com.bm.kakaopay.dto.history.AccountHistoryAddRequest;
import com.bm.kakaopay.dto.history.AccountHistoryAddResponse;
import com.bm.kakaopay.exception.AccountHistoryFailException;
import com.bm.kakaopay.repository.AccountHistoryRepository;
import com.bm.kakaopay.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionalException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.bm.kakaopay.code.Result.ACCOUNT_HISTORY_ADD_SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountHistoryService {

    private final AccountRepository accountRepository;

    private final AccountHistoryRepository accountHistoryRepository;

    @Transactional(rollbackFor = TransactionalException.class)
    public Optional<AccountHistoryAddResponse> addAccountHistory(AccountHistoryAddRequest accountHistoryAddRequest) {

        accountRepository.findByUserAccount(accountHistoryAddRequest.getUserAccount()).orElseThrow(()-> new AccountHistoryFailException("계좌번호가 존재하지 않습니다"));

        LocalDate time =  LocalDate.now();

        AccountHistory accountHistory = AccountHistory.builder()
                .userAccount(accountHistoryAddRequest.getUserAccount())
                .is_tsmt(accountHistoryAddRequest.getIsTsmt())
                .amount(accountHistoryAddRequest.getAmount())
                .createAt(time)
                .build();


        accountHistoryRepository.save(accountHistory);

        Optional<AccountHistoryAddResponse> accountHistoryAddResponse = Optional.ofNullable(AccountHistoryAddResponse.builder()
                .returnCode(ACCOUNT_HISTORY_ADD_SUCCESS.toString())
                .returnMessage(ACCOUNT_HISTORY_ADD_SUCCESS.getMsg())
                .build());

        return accountHistoryAddResponse;
    }

    @Transactional(readOnly = true)
    public List<AccountHistory> listAccountHistory() {
        return accountHistoryRepository.findAll();
    }

}

