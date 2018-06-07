package org.delphy.tokenherocms.common;

import lombok.Data;
import org.delphy.tokenherocms.util.TimeUtil;

import java.io.Serializable;

/**
 * @author mutouji
 */
@Data
public class RestResult<T> implements Serializable {
    private int code;
    private String msg;
    private long timestamp = TimeUtil.getCurrentSeconds();
    private T data;

    private RestResult() {

    }

    public RestResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public RestResult(int code) {
        this.code = code;
        this.msg = "success";
        this.data = null;
    }
}
