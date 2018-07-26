package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author mutouji
 */
@Data
@Document(collection = "withdraw")
public class Withdraw implements Serializable {
    /**
     * 15271451776124313  微秒 + 4位
     */
    @Id
    private String id;
    /**
     * 运气
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 15256961166880919--- 微秒 + 4位
     */
    private String userId;
    /**
     * 0x9e2283F352B2a970500fdDc780A34eC02B841658
     */
    private String address;
    /**
     * 41.09
     */
    private Double count;
    /**
     * default = 1;
     */
    private Double fee;
    /**
     * 0 未打 1 已打
     */
    private Long status;
    /**
     * 1527145177
     */
    private Long create;
    /**
     * 0
     */
    private Long delete;

    public Withdraw() {
        fee = 1.0;
    }
}
