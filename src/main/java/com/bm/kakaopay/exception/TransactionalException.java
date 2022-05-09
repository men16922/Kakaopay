package com.bm.kakaopay.exception;

public class TransactionalException extends RuntimeException {

    public TransactionalException(final Throwable e) {
        super("transaction 도중 실패하였습니다", e);
    }
}

