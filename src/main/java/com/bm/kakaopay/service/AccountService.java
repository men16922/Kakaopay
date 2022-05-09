package com.bm.kakaopay.service;

import com.bm.kakaopay.domain.Account;
import com.bm.kakaopay.dto.accounts.AccountAddRequest;
import com.bm.kakaopay.dto.accounts.AccountAddResponse;
import com.bm.kakaopay.exception.AccountFailException;
import com.bm.kakaopay.repository.AccountRepository;
import com.bm.kakaopay.repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionalException;
import java.util.List;
import java.util.Optional;

import static com.bm.kakaopay.code.Result.ACCOUNT_ADD_SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final KakaoUserRepository kakaoUserRepository;

    @Transactional(rollbackFor = TransactionalException.class)
    public Optional<AccountAddResponse> addAccount(AccountAddRequest accountAddRequest) {

        kakaoUserRepository.findById(accountAddRequest.getUserId()).orElseThrow(() -> new AccountFailException("user_id 조회에 실패하였습니다."));

        if(accountRepository.findByUserAccount(accountAddRequest.getUserAccount()) != null) {
            throw new AccountFailException("중복된 계좌번호입니다");
        }


        Account account = Account.builder()
                .userId(accountAddRequest.getUserId())
                .userAccount(accountAddRequest.getUserAccount())
                .build();

        accountRepository.save(account);

        Optional<AccountAddResponse> accountAddResponse = Optional.ofNullable(AccountAddResponse.builder()
                .returnCode(ACCOUNT_ADD_SUCCESS.toString())
                .returnMessage(ACCOUNT_ADD_SUCCESS.getMsg())
                .build());

        return accountAddResponse;
    }

    @Transactional(readOnly = true)
    public List<Account> listAccount() {
        return accountRepository.findAll();
    }
}

