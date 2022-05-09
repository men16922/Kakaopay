package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.accounts.AccountAddRequest;
import com.bm.kakaopay.dto.accounts.AccountAddResponse;
import com.bm.kakaopay.dto.accounts.AccountDto;
import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.exception.AccountFailException;
import com.bm.kakaopay.service.AccountService;
import com.bm.kakaopay.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "add")
    public ApiResult<AccountAddResponse> add(@Valid @RequestBody AccountAddRequest accountAddRequest) {
        return ApiUtils.success(accountService.addAccount(accountAddRequest)
                .orElseThrow(() -> new AccountFailException("계좌 추가에 실패하였습니다."))
        );
    }

    @GetMapping(path = "list")
    public ApiResult<List<AccountDto>> list() {
        return ApiUtils.success(accountService.listAccount().stream()
        .map(AccountDto::new)
        .collect(toList())
        );
    }
}

