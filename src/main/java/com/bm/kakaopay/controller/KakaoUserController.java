package com.bm.kakaopay.controller;

import com.bm.kakaopay.dto.general.ApiResult;
import com.bm.kakaopay.dto.users.*;
import com.bm.kakaopay.exception.UserFailException;
import com.bm.kakaopay.service.KakaoUserService;
import com.bm.kakaopay.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/")
public class KakaoUserController {

    private final KakaoUserService kakaoUserService;

    @PostMapping(path = "add")
    public ApiResult<UserAddResponse> add(@Valid @RequestBody UserAddRequest userAddRequest) {
        return ApiUtils.success(kakaoUserService.addUser(userAddRequest)
                .orElseThrow(() -> new UserFailException("사용자 추가에 실패하였습니다."))
        );
    }

    @GetMapping(path = "list")
    public ApiResult<List<KakaoUserDto>> list() {
        return ApiUtils.success(kakaoUserService.listUser().stream()
        .map(KakaoUserDto::new)
        .collect(toList())
        );
    }

}

