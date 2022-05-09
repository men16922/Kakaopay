package com.bm.kakaopay.exception;

public class AccountHistoryFailException extends RuntimeException{

    public AccountHistoryFailException(String msg) {
        super(msg);
    }

    public AccountHistoryFailException(final Throwable e) {
        super("계좌내역 관련 작업 도중 실패하였습니다", e);
    }
}

