package org.delphy.tokenherocms.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.util.TimeUtil;

/**
 * @author mutouji
 */
@ApiModel(description = "创建活动时，需要传递过来的实体")
@Data
public class ActivityVo {
    /**
     * 预测EOS在20点10分的收盘价是多少？允许误差范围0.1%
     */
    @ApiModelProperty()
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
     * 获取结果时间1,523,621,520 单位秒
     */
    private Long end;
    /**
     * 数据源 币安
     */
    private String datasource;
    /**
     * 状态: 未开始0，进行中1，锁定中2，清算中3，已结束4, 5
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
     * 类型 小程序1 H5活动2
     */
    private Long type;

    private EnumError checkFields() {
        if (start < end) {
            return EnumError.ACTIVITY_END;
        }
        return EnumError.SUCCESS;
    }

    public EnumError initActivity(Activity activity) {
        activity.setDelete(0L);
        activity.setCreate(TimeUtil.getCurrentSeconds());
        activity.setType(type);

        return EnumError.SUCCESS;
    }
}
