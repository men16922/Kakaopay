package com.bm.kakaopay.exception;

public class UserFailException extends RuntimeException{

    public UserFailException(String msg) {
        super(msg);
    }

    public UserFailException(final Throwable e) {
        super("사용자 관련 작업 도중 실패하였습니다", e);
    }
}

