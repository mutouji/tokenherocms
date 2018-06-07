package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author mutouji
 */
@Data
@Document(collection = "withdraw")
public class Withdraw {
    @Id
    private String id;
    private String name;
    private String userId;
    private String address;
    private Double count;
    private Double status;
    private Long create;
    private Long delete;
}
