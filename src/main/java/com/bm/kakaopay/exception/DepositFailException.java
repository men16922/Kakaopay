package com.bm.kakaopay.exception;

public class DepositFailException extends RuntimeException{

    public DepositFailException(String msg) {
        super(msg);
    }

    public DepositFailException(final Throwable e) {
        super("예치금 관련 작업 도중 실패하였습니다", e);
    }
}

