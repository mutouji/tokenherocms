package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author mutouji
 */
@Data
@Document(collection = "forecast")
@CompoundIndexes({
        @CompoundIndex(name="user_activity", def = "{activityId: -1, userId: -1}")
})
public class Forecast implements Serializable {
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
     * 0 剩余预测次数，默认为2
     */
    private Long freeNum;
    /**
     * 0
     */
    private Double reward;
    /**
     * 1,525,867,882
     */
    private Long rewardTime;
    /**
     * 获益比例 0.99 ==> 99%
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
