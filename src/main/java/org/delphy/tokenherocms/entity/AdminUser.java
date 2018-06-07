package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author mutouji
 */
@Data
@Document(collection = "adminuser")
public class AdminUser {
    @Id
    private String id;
    private String account;
    /**
     * md5 加密
     */
    private String pwd;
    private String url;
    /**
     * 1
     */
    private Long power;
    /**
     * admin
     */
    private String name;
    /**
     * admin
     */
    private String duties;
    private String phone;
    private String jobNum;

    // TODO: clear this field...
//    private Array setUp;

    private Long create;
}
