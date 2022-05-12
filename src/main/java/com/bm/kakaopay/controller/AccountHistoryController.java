package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.dto.history.AccountHistoryAddRequest;
import com.bm.kakaopay.dto.history.AccountHistoryAddResponse;
import com.bm.kakaopay.dto.history.AccountHistoryDto;
import com.bm.kakaopay.exception.AccountHistoryFailException;
import com.bm.kakaopay.service.AccountHistoryService;
import com.bm.kakaopay.util.ApiUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * 계좌내역 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "계좌내역 추가/조회")
@RequestMapping("api/accounts-history/")
public class AccountHistoryController {

    private final AccountHistoryService accountHistoryService;

    @PostMapping(path = "add")
    public ApiResult<AccountHistoryAddResponse> add(@Valid @RequestBody AccountHistoryAddRequest accountHistoryAddRequest) {
        return ApiUtils.success(accountHistoryService.addAccountHistory(accountHistoryAddRequest)
                .orElseThrow(() -> new AccountHistoryFailException("계좌내역 추가에 실패하였습니다."))
        );
    }

    @GetMapping(path = "list")
    public ApiResult<List<AccountHistoryDto>> list() {
        return ApiUtils.success(accountHistoryService.listAccountHistory().stream()
        .map(AccountHistoryDto::new)
        .collect(toList())
        );
    }
}

