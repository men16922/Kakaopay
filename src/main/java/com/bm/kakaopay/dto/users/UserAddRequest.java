package com.bm.kakaopay.dto.users;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAddRequest {

    @NotNull
    @Required
    @NotBlank(message = "user_id is Mandatory")
    private Long userId;

    @NotNull  @Required
    @NotBlank(message = "name is Mandatory")
    private String name;

    @NotNull @Required
    @NotBlank(message = "age is Mandatory")
    private int age;

}

