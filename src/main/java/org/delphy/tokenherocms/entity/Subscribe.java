package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author mutouji
 */
@Data
@Document(collection = "subscribe")
public class Subscribe {
    /**
     * 1523432456622120
     */
    @Id
    private String id;
    /**
     * 15234324253659807
     */
    private String userId;
    /**
     * 15234271896699455
     */
    private String activityId;
    /**
     * 0没删
     */
    private Long delete;
}
