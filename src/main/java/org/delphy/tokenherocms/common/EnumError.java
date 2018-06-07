package org.delphy.tokenherocms.common;

public enum EnumError {
    /**
     * 1004, token验证失败
     */
    INCORRECT_TOKEN(1004, "token验证失败");

    EnumError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
