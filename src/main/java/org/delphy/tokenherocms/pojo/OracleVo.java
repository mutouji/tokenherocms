package org.delphy.tokenherocms.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mutouji
 */
@Data
public class OracleVo<T> implements Serializable {
    private int code;
    private T data;
}
