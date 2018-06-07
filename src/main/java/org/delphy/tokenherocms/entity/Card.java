package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author mutouji
 */
@Data
@Document(collection = "card")
public class Card {
    @Id
    private String id;
    private String userId;
    /**
     * 634965
     * warning --- old field name is "No"
     */
    private Long number;
    /**
     * 是否用过 1用过 0没用过
     */
    private Long isUse;
    /**
     * 秒: 1523431028
     */
    private Long create;
    /**
     * 0
     */
    private Long delete;
}
