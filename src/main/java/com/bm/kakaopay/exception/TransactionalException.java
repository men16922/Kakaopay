package com.bm.kakaopay.exception;

/**
 * 트랜잭션 관련 Exception 처리
 */
public class TransactionalException extends RuntimeException {

    public TransactionalException(final Throwable e) {
        super("transaction 도중 실패하였습니다", e);
    }
}

