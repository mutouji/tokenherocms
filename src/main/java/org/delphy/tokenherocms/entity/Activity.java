package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "activity")
public class Activity {
    @Id
    private String id;
    // 预测NEO比价
    private String title;
    // 开始时间 1523532600
    private Long start;
    // 奖池 100
    private Long pond;
    // 持续时间 10 分钟
    private Long hold;
    // 锁定 5 分钟
    private Long lockTime;
    // 货币 "NEO"
    private String pair;
    // 对标货币 "USDT"
    private String base;
    // 17分钟后结束？ 1523533620
    private Long end;
    // 数据源 binance
    private String datasource;
    // 状态 未开始 进行中 锁定中 清算中 已结束
    private Long status;

    //////////////////
    // 是否清算
    private Long isSettlement;
    // 延迟了
    private Long delayed;
    // 结果是多少
    private Long result;
    // 从oracle获取结果的时间
    private Long getOracleTime;
    //
    private Long oracleId;
    // ？
    private Long type;
    private Long create;
    private Long delete;
}
