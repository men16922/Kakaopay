package com.bm.kakaopay.dto.users;

import com.bm.kakaopay.domain.KakaoUser;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserDto {

    private Long userId;

    private String name;

    private Integer age;

    private LocalDate createAt;

    public KakaoUserDto(KakaoUser kakaoUser){
        copyProperties(kakaoUser, this);
    }
}

