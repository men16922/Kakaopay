package com.bm.kakaopay.dto.general;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Api에 대한 응답 Generice 형태로 선언
 * @param <T>
 */
public class ApiResult<T> {

    private final boolean success;
    private final T response;
    private final ApiError error;


    public ApiResult(boolean success, T response, ApiError error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public ApiError getError() {
        return error;
    }

    public T getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("success", success)
                .append("response", response)
                .append("error", error)
                .toString();
    }
}

