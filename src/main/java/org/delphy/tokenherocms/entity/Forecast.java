package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

/**
 * price used in id
 */
@Data
class Price {
    private Double price;
    private String rate;
    private Long time;
}
/**
 * @author mutouji
 */
@Data
@Document(collection = "forecast")
public class Forecast {
    /**
     * 17位 15258669902305444
     */
    @Id
    private String id;
    /**
     * 15239202292428653
     */
    private String userId;
    /**
     * 15258565502917858
     */
    private String activityId;
    /**
     * 0
     * TODO: freeNum是啥意思
     */
    private Long freeNum;
    /**
     * 0
     */
    private Long reward;
    /**
     * 1,525,867,882
     */
    private Long rewardTime;
    /**
     * 0.001
     * TODO: 啥意思
     */
    private Double rewardRatio;
    /**
     * 数组
     * [0]: price:749.55 rate:3  time:1,525,867,486
     * [1]: price:741.55 rate:85 time:1,525,866,990
     */
    private List<Price> prices;
    /**
     * price:749.55 rate:3  time:1,525,867,486
     */
    private Price last;
    /**
     * 1,525,866,990
     */
    private Long create;
    private Long delete;
}
