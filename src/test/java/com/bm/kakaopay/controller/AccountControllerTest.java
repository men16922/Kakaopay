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

/**
 * 계좌 관련 API 테스트 클래스
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountControllerTest {

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("계좌 추가 테스트(실패)")
    void addAccountsfail() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/accounts/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":66,\"user_account\":\"1000-44\"}")
        );

        result.andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(handler().handlerType(AccountController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.error").exists())
        ;
    }

    @Test
    @DisplayName("계좌 추가 테스트(성공)")
    void addAccountsSucess() throws Exception {

        ResultActions result = mockMvc.perform(
                post("/api/accounts/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"user_id\":8,\"user_account\":\"1000-60\"}")
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AccountController.class))
                .andExpect(handler().methodName("add"))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.response.return_code").exists())
                .andExpect(jsonPath("$.response.return_code").isString())
                .andExpect(jsonPath("$.response.return_message").exists())
                .andExpect(jsonPath("$.response.return_message").isString())
        ;
    }

    @Test
    @DisplayName("계좌 목록 조회 테스트")
    void listAccounts() throws Exception {

        ResultActions result = mockMvc.perform(
                get("/api/accounts/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(AccountController.class))
                .andExpect(handler().methodName("list"))
                .andExpect(jsonPath("$.success", is(true)))
        ;
    }
}