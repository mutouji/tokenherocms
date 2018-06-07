package org.delphy.tokenherocms.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

//{
//        "ret": true,
//        "data": {
//        "list": [
//        {
//        "id": "15233591272898350",
//        "name": "乔+1",
//        "openId": "oI3TD5NLhoIEs-Z10MGG3pXcuaok",
//        "avatar": "https:\/\/wx.qlogo.cn\/mmopen\/vi_32\/cQfOl06aaSvgmbN64TichLkhKsMnoicywicwxLOhUia25EGlzF7MMNeS24SSzLuAdxUsktr4re98S3DiaKNtwXicBIHA\/0",
//        "phone": "",
//        "countForecast": 21,
//        "victories": 6,
//        "dpy": 3.2,
//        "totalDpy": 13.2,
//        "probability": "28.57%",
//        "countCard": 0,
//        "countUseCard": 0,
//        "create": "2018-04-10 19:18:47"
//        },
//        {
//        "id": "15233591148464474",
//        "name": "Mike 肺",
//        "openId": "oI3TD5NRj6PfnZFKNQYDM1NrcRko",
//        "avatar": "https:\/\/wx.qlogo.cn\/mmopen\/vi_32\/cSUwoGLtwMia9R9mkpAQOCtXYOcH38nOPYuul9FMaGrDGCOicPhy58Aq60PmYRvzdq7dQMZ62JV763elEOjxGibpg\/0",
//        "phone": "",
//        "countForecast": 21,
//        "victories": 3,
//        "dpy": 1.7,
//        "totalDpy": 1.7,
//        "probability": "14.29%",
//        "countCard": 4,
//        "countUseCard": 0,
//        "create": "2018-04-10 19:18:34"
//        },
//        {
//        "id": "15233590196834402",
//        "name": "1N",
//        "openId": "oI3TD5FDvMqnDwhS5u8m-UzmYOnU",
//        "avatar": "https:\/\/wx.qlogo.cn\/mmopen\/vi_32\/PiajxSqBRaELXE4u7YibNUPcOciaTC6niaqRSdGl7ex7XvibnQTDCicj3cWoRrxgcg7CC5aRWYicqO7frsMDXArwsZOXw\/0",
//        "phone": "",
//        "countForecast": 27,
//        "victories": 10,
//        "dpy": 11.69,
//        "totalDpy": 11.69,
//        "probability": "37.04%",
//        "countCard": 2,
//        "countUseCard": 0,
//        "create": "2018-04-10 19:16:59"
//        }
//        ],
//        "count": 6828
//        },
//        "msg": "success"
//        }

/**
 * @author mutouji
 */
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    /**
     * 乔+1
     */
    private String name;
    /**
     * oI3TD5NLhoIEs-Z10MGG3pXcuaok
     */
    private String openId;
    /**
     * https:\/\/wx.qlogo.cn\/mmopen\/vi_32\/cQfOl06aaSvgmbN64TichLkhKsMnoicywicwxLOhUia25EGlzF7MMNeS24SSzLuAdxUsktr4re98S3DiaKNtwXicBIHA\/0
     */
    private String avatar;
    /**
     *
     */
    private String phone;
    /**
     * 预测次数
     */
    private Long countForecast;
    private Long victories;
    /**
     * 新probability，28.57%
     */
    private String probability;
    /**
     * 3.2
     */
    private Double dpy;
    /**
     * 13.2
     */
    private Double totalDpy;
    /**
     * 改价卡数量
     */
    private Long countCard;
    private Long countUseCard;
    /**
     * 已经使用的改价卡
     */
    private Long create;
    private Long delete;
}
