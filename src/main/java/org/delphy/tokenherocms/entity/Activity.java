package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author mutouji
 */
@Data
@Document(collection = "activity")
public class Activity implements Serializable {
    /**
     * 15236027302838709 微秒 + 4位
     */
    @Id
    private String id;
    /**
     * 预测EOS在20点10分的收盘价是多少？允许误差范围0.1%
     */
    private String title;
    /**
     * 开始时间1,523,620,500 单位秒
     */
    private Long start;
    /**
     * 奖金池400 单位dpy
     */
    private Long pond;
    /**
     * 持续时间10 单位分钟
     */
    private Long hold;
    /**
     * 锁定时间 5 单位分钟
     */
    private Long lockTime;
    /**
     * 源币种  EOS
     */
    private String pair;
    /**
     * 参照币种  ETH
     */
    private String base;
    /**
     * 市场结束时间 start + (hold  + lockTime) * 60 + settlement time (2 * 60)
     */
    private Long end;
    /**
     * 数据源 币安
     */
    private String datasource;
    /**
     * 中奖范围0.001
     */
    private Double rewardRatio;
    /**
     * 状态: 未开始1，进行中2，锁定中3，清算中4，已结束5
     */
    private Long status;
    /**
     * 是否清算 否0, 是1
     */
    private Long isSettlement;
    /**
     * 延迟？
     */
    private Long delayed;
    /**
     *  结果 0.01805
     */
    private Double result;
    /**
     * 获取oracle 结果的时间 秒 1,523,621,460
     */
    private Long getOracleTime;
    /**
     * oracle id  102
     */
    private Long oracleId;
    /**
     * oracle type
     * 类型 币安1 篮球2 足球3
     */
    private Long type;
    /**
     * 0 微信小程序活动， 1 h5 活动
     */
    private Long mode;
    /**
     * 本条记录的创建时间1,523,602,730
     */
    private Long create;
    /**
     * 是否删除 0 没删除 1 已经删除
     */
    private Long delete;
}

//{
//        "ret": true,
//        "data": {
//        "list": [
//        {
//        "id": "15282855095881481",
//        "title": "预测ETH在20点02分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-06-06 19:46",
//        "pond": 600,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1528286580,
//        "datasource": "币安",
//        "status": 4,
//        "isSettlement": 0,
//        "delayed": 0,
//        "result": 0,
//        "getOrcaleTime": "2018-06-06 20:02:00",
//        "oracleId": 33,
//        "type": 1,
//        "create": 1528285509,
//        "delete": 0
//        },
//        {
//        "id": "15281024794969994",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-06-04 19:55",
//        "pond": 300,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1528114320,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 593.98,
//        "getOrcaleTime": "2018-06-04 20:11:00",
//        "oracleId": 31,
//        "type": 1,
//        "create": 1528102479,
//        "delete": 0
//        },
//        {
//        "id": "15281023597538673",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-06-04 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1528112820,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.022786,
//        "getOrcaleTime": "2018-06-04 19:46:00",
//        "oracleId": 30,
//        "type": 1,
//        "create": 1528102359,
//        "delete": 0
//        },
//        {
//        "id": "15278404701967672",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-06-01 19:55",
//        "pond": 300,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1527855120,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 575.45,
//        "getOrcaleTime": "2018-06-01 20:11:00",
//        "oracleId": 29,
//        "type": 1,
//        "create": 1527840470,
//        "delete": 0
//        },
//        {
//        "id": "15278404009447122",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-06-01 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1527853620,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.020826,
//        "getOrcaleTime": "2018-06-01 19:46:00",
//        "oracleId": 28,
//        "type": 1,
//        "create": 1527840400,
//        "delete": 0
//        },
//        {
//        "id": "1527665738112026",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-05-30 19:55",
//        "pond": 400,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1527682320,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 571.85,
//        "getOrcaleTime": "2018-05-30 20:11:00",
//        "oracleId": 22,
//        "type": 1,
//        "create": 1527665738,
//        "delete": 0
//        },
//        {
//        "id": "15276656889322871",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-05-30 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1527680820,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.021628,
//        "getOrcaleTime": "2018-05-30 19:46:00",
//        "oracleId": 21,
//        "type": 1,
//        "create": 1527665688,
//        "delete": 0
//        },
//        {
//        "id": "15274896259009876",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-05-28 19:55",
//        "pond": 400,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1527509520,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 522,
//        "getOrcaleTime": "2018-05-28 20:11:00",
//        "oracleId": 20,
//        "type": 1,
//        "create": 1527489625,
//        "delete": 0
//        },
//        {
//        "id": "15274895519772874",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-05-28 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1527508020,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.022291,
//        "getOrcaleTime": "2018-05-28 19:46:00",
//        "oracleId": 17,
//        "type": 1,
//        "create": 1527489551,
//        "delete": 0
//        },
//        {
//        "id": "15272294834143383",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-05-25 19:55",
//        "pond": 400,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1527250320,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 604.32,
//        "getOrcaleTime": "2018-05-25 20:11:00",
//        "oracleId": 16,
//        "type": 1,
//        "create": 1527229483,
//        "delete": 0
//        },
//        {
//        "id": "1527229379604893",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-05-25 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1527248820,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.020389,
//        "getOrcaleTime": "2018-05-25 19:46:00",
//        "oracleId": 12,
//        "type": 1,
//        "create": 1527229379,
//        "delete": 0
//        },
//        {
//        "id": "15270806479735653",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-05-23 21:08",
//        "pond": 400,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1527081900,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 622.03,
//        "getOrcaleTime": "2018-05-23 21:24:00",
//        "oracleId": 11,
//        "type": 1,
//        "create": 1527080647,
//        "delete": 0
//        },
//        {
//        "id": "15270687578433936",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-05-23 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1527076020,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.0183,
//        "getOrcaleTime": "2018-05-23 19:46:00",
//        "oracleId": 9,
//        "type": 1,
//        "create": 1527068757,
//        "delete": 0
//        },
//        {
//        "id": "15268978271508318",
//        "title": "预测ETH在20点10分的收盘价是多少？命中范围0.1%",
//        "start": "2018-05-21 19:55",
//        "pond": 400,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "ETH",
//        "base": "USDT",
//        "end": 1526904720,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 709.82,
//        "getOrcaleTime": "2018-05-21 20:11:00",
//        "oracleId": 5,
//        "type": 1,
//        "create": 1526897827,
//        "delete": 0
//        },
//        {
//        "id": "15268978812449207",
//        "title": "预测EOS在19点45分的收盘价是多少？命中范围为0.1%",
//        "start": "2018-05-21 19:30",
//        "pond": 100,
//        "hold": 10,
//        "lockTime": 5,
//        "pair": "EOS",
//        "base": "ETH",
//        "end": 1526903220,
//        "datasource": "币安",
//        "status": 5,
//        "isSettlement": 1,
//        "delayed": 0,
//        "result": 0.019026,
//        "getOrcaleTime": "2018-05-21 19:46:00",
//        "oracleId": 6,
//        "type": 1,
//        "create": 1526897881,
//        "delete": 0
//        }
//        ],
//        "count": 65
//        },
//        "msg": "success"
//        }