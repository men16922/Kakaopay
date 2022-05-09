package com.bm.kakaopay.service;

import com.bm.kakaopay.domain.KakaoUser;
import com.bm.kakaopay.dto.users.UserAddRequest;
import com.bm.kakaopay.dto.users.UserAddResponse;
import com.bm.kakaopay.repository.KakaoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionalException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.bm.kakaopay.code.Result.USER_ADD_SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoUserService {

    private final KakaoUserRepository kakaoUserRepository;

    @Transactional(rollbackFor = TransactionalException.class)
    public Optional<UserAddResponse> addUser(UserAddRequest userAddRequest) {

        LocalDate time =  LocalDate.now();
        KakaoUser kakaoUser = KakaoUser.builder()
                .userId(userAddRequest.getUserId())
                .name(userAddRequest.getName())
                .age(userAddRequest.getAge())
                .createAt(time)
                .build();

        kakaoUserRepository.save(kakaoUser);

        Optional<UserAddResponse> userAddResponse = Optional.ofNullable(UserAddResponse.builder()
                .returnCode(USER_ADD_SUCCESS.toString())
                .returnMessage(USER_ADD_SUCCESS.getMsg())
                .build());

        return userAddResponse;
    }

    @Transactional(readOnly = true)
    public List<KakaoUser> listUser() {
       return kakaoUserRepository.findAll();
    }


}

