package com.bm.kakaopay.exception;

public class CsvException extends RuntimeException {

    public CsvException(final Throwable e) {
        super("CSV 파일 작업 도중 실패하였습니다", e);
    }
}
