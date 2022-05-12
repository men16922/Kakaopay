package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.accounts.AccountAddRequest;
import com.bm.kakaopay.dto.accounts.AccountAddResponse;
import com.bm.kakaopay.dto.accounts.AccountDto;
import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.exception.AccountFailException;
import com.bm.kakaopay.service.AccountService;
import com.bm.kakaopay.util.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * 계좌 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "계좌 추가/조회")
@RequestMapping("api/accounts/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "add")
    @Operation(summary = "계좌 추가",
            description = "계좌 추가")
    public ApiResult<AccountAddResponse> add(@Valid @RequestBody AccountAddRequest accountAddRequest) {
        return ApiUtils.success(accountService.addAccount(accountAddRequest)
                .orElseThrow(() -> new AccountFailException("계좌 추가에 실패하였습니다."))
        );
    }

    @GetMapping(path = "list")
    @Operation(summary = "계좌 목록",
            description = "계좌 목록")
    public ApiResult<List<AccountDto>> list() {
        return ApiUtils.success(accountService.listAccount().stream()
        .map(AccountDto::new)
        .collect(toList())
        );
    }
}

