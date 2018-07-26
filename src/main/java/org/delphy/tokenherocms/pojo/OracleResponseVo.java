package org.delphy.tokenherocms.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mutouji
 */
@Data
public class OracleResponseVo<T> implements Serializable {
    private int code;
    private T data;
}
