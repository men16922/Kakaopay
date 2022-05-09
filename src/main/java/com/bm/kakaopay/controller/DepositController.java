package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.deposit.AgeGroupAmountDto;
import com.bm.kakaopay.dto.deposit.YearTotalAmountDto;
import com.bm.kakaopay.dto.deposit.TimeUserDescDto;
import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.dto.users.UserAccountRequest;
import com.bm.kakaopay.dto.users.UserAccountResponse;
import com.bm.kakaopay.service.DepositService;
import com.bm.kakaopay.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/deposit/")
public class DepositController {

    private final DepositService depositService;

    @GetMapping(path = "accounts")
    public ApiResult<List<UserAccountResponse>> accounts(@Valid @RequestBody UserAccountRequest userAccountRequest) {
        return ApiUtils.success(depositService.userAccounts(userAccountRequest));
    }

    @GetMapping(path = "age-group-amount")
    public ApiResult<List<AgeGroupAmountDto>> ageGroupAmount() {
        return ApiUtils.success(depositService.ageGroupAmount());
    }

    @GetMapping(path = "year-total-amount")
    public ApiResult<YearTotalAmountDto> yearTotalAmount(@RequestParam String year) {
        return ApiUtils.success(depositService.yearTotalAmount(year));
    }

    @GetMapping(path = "time-user-desc")
    public ApiResult<List<TimeUserDescDto>> timeUserDesc(@RequestParam("start_year") String startYear, @RequestParam("end_year") String endYear) {
        return ApiUtils.success(depositService.timeUserDesc(startYear, endYear));
    }

}

