package org.delphy.tokenherocms.common;

/**
 * @author mutouji
 */
public enum EnumError {
    /**
     *
     */
    SUCCESS(0, "success"),
    /**
     * 1004, token验证失败
     */
    INCORRECT_TOKEN(1004, "token验证失败"),

    ACTIVITY_END_GT_START(1201, "获取结果时间应大于开始时间"),
    ACTIVITY_NULL_FIELD(1202, "缺少必要参数"),
    ACTIVITY_STATIC_FIELD(1203, "市场，开始，持续，锁定，获取结果时间，为静态字段，请误修改"),
    ACTIVITY_NOT_EXIST(1204, "该活动不存在"),

    /**
     *
     */
    ORACLE_CODE(1301, "请求oracle服务失败，code={0},msg={1}"),

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
