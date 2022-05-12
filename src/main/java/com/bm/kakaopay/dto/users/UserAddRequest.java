package com.bm.kakaopay.dto.users;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 사용자 추가 요청 Dto
 */
@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAddRequest {

    @NotNull
    @Required
    @NotBlank(message = "user_id is Mandatory")
    @Schema(description = "사용자ID", defaultValue = "60")
    private Long userId;

    @NotNull  @Required
    @NotBlank(message = "name is Mandatory")
    @Schema(description = "이름", defaultValue = "Paul")
    private String name;

    @NotNull @Required
    @NotBlank(message = "age is Mandatory")
    @Schema(description = "나이", defaultValue = "30")
    private int age;

}

