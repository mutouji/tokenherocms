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
    private String pwd;
    private String url;
    private Long power;
    private String name;
    private String duties;
    private String phone;
    private String jobNum;
    private Long create;
}
