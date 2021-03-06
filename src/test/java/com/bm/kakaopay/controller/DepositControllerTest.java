package com.bm.kakaopay.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 예치금 관련 API 테스트 클래스
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DepositControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("사용자의 계좌별 예치금 조회 테스트")
    void accountUsers() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/deposit/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":8}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepositController.class))
                .andExpect(handler().methodName("accounts"))
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }

    @Test
    @DisplayName("사용자 나이대 별로, 평균 예치금 조회 테스트")
    void ageGroupAmount() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/deposit/age-group-amount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepositController.class))
                .andExpect(handler().methodName("ageGroupAmount"))
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }

    @Test
    @DisplayName("해당년도의 예치금 총액 조회 테스트")
    void yearTotalAmount() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/deposit/year-total-amount?year=2020")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepositController.class))
                .andExpect(handler().methodName("yearTotalAmount"))
                .andExpect(jsonPath("$.response.total_deposit").exists())
                .andExpect(jsonPath("$.response.total_deposit").isNumber())
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }

    @Test
    @DisplayName("기간을 입력받아, 돈을 많이 예치한 사용자 순으로 정렬 조회 테스트")
    void timeUserDesc() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/deposit/time-user-desc?start_year=2020&end_year=2021")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(DepositController.class))
                .andExpect(handler().methodName("timeUserDesc"))
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }
}