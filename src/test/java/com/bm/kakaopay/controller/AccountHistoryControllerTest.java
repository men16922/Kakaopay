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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountHistoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("계좌내역 추가 테스트(실패)")
    void addAccountsHistoryFail() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/accounts-history/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_account\":\"1000-66\", \"is_tsmt\":\"Y\", \"amount\":8000}")
        );

        result.andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(handler().handlerType(AccountHistoryController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
        ;
    }

    @Test
    @DisplayName("계좌내역 추가 테스트(성공)")
    void addAccountsHistorySuccess() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/accounts-history/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_account\":\"1000-44\", \"is_tsmt\":\"Y\", \"amount\":8000}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AccountHistoryController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.return_code").exists())
                .andExpect(jsonPath("$.response.return_code").isString())
                .andExpect(jsonPath("$.response.return_message").exists())
                .andExpect(jsonPath("$.response.return_message").isString())
        ;
    }

    @Test
    @DisplayName("계좌내역 목록 조회 테스트")
    void listAccountsHistory() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/accounts-history/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AccountHistoryController.class))
                .andExpect(handler().methodName("list"))
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }
}