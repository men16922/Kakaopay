package com.bm.kakaopay.exception;

/**
 * 계좌관련 실패 Exception 처리
 */
public class AccountFailException extends RuntimeException{

    public AccountFailException(String msg) {
        super(msg);
    }

    public AccountFailException(final Throwable e) {
        super("계좌 관련 작업 도중 실패하였습니다", e);
    }
}

