package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.deposit.AgeGroupAmountDto;
import com.bm.kakaopay.dto.deposit.YearTotalAmountDto;
import com.bm.kakaopay.dto.deposit.TimeUserDescDto;
import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.dto.deposit.UserAccountRequest;
import com.bm.kakaopay.dto.deposit.UserAccountResponse;
import com.bm.kakaopay.service.DepositService;
import com.bm.kakaopay.util.ApiUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 예치금 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "예치금 추가과제")
@RequestMapping("api/deposit/")
public class DepositController {

    private final DepositService depositService;

    @PostMapping(path = "accounts")
    @Operation(summary = "사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오",
            description = "사용자를 입력받아, 사용자의 계좌별 예치금을 출력하시오")
    public ApiResult<List<UserAccountResponse>> accounts(@Valid @RequestBody UserAccountRequest userAccountRequest) {
        return ApiUtils.success(depositService.userAccounts(userAccountRequest));
    }

    @GetMapping(path = "age-group-amount")
    @Operation(summary = "사용자 나이대 별로, 평균 예치금을 출력하시오",
            description = "사용자 나이대 별로, 평균 예치금을 출력하시오")
    public ApiResult<List<AgeGroupAmountDto>> ageGroupAmount() {
        return ApiUtils.success(depositService.ageGroupAmount());
    }

    @GetMapping(path = "year-total-amount")
    @Operation(summary = "년도를 입력받아, 해당년도의 예치금 총액을 출력하시오",
            description = "년도를 입력받아, 해당년도의 예치금 총액을 출력하시오")
    @ApiImplicitParam(name = "year", value = "2021", required = true)
    public ApiResult<YearTotalAmountDto> yearTotalAmount(@RequestParam String year) {
        return ApiUtils.success(depositService.yearTotalAmount(year));
    }

    @GetMapping(path = "time-user-desc")
    @Operation(summary = "기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오",
            description = "기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬해서 출력하시오")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start_year", value = "2020", required = true),
            @ApiImplicitParam(name = "end_year", value = "2021", required = true)
    })
    public ApiResult<List<TimeUserDescDto>> timeUserDesc(@RequestParam("start_year") String startYear, @RequestParam("end_year") String endYear) {
        return ApiUtils.success(depositService.timeUserDesc(startYear, endYear));
    }

}

