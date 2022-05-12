package com.bm.kakaopay.dto.users;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotNull;

/**
 * 사용자 추가 응답 Dto
 */
@ToString
@Setter
@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAddResponse {

    @NotNull
    @Required
    private String returnCode;

    @NotNull
    @Required
    private String returnMessage;
}

