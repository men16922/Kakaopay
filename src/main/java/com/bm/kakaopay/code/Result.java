package com.bm.kakaopay.code;

public enum Result {
    USER_ADD_SUCCESS("user add succeeded"),
    ACCOUNT_ADD_SUCCESS("account add succeeded"),
    ACCOUNT_HISTORY_ADD_SUCCESS("account history add succeeded");


    final private String msg;

    public String getMsg() {
        return msg;
    }
    private Result(String msg){
        this.msg = msg;
    }
}
