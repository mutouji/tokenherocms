package org.delphy.tokenherocms.common;

public enum EnumError {
    /**
     *
     */
    SUCCESS(0, "success"),
    /**
     * 1004, token验证失败
     */
    INCORRECT_TOKEN(1004, "token验证失败"),

    ACTIVITY_END(1201, "获取结果时间应大于开始时间"),

    /**
     * tag of end
     */
    ENUMERROR_END(9999, "最后一个错误号");

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
