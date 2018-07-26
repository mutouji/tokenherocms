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
    @ApiModelProperty(value="标题", required = true, example = "预测币安BTC/USDT价格")
    private String title;
    /**
     * 开始时间1,523,620,500 单位秒
     */
    @ApiModelProperty(value="开始时间 单位秒", required = true, example = "1538700000")
    private Long start;
    /**
     * 奖金池400 单位dpy
     */
    @ApiModelProperty(value="奖池 单位dpy", required = true, example = "400")
    private Long pond;
    /**
     * 持续时间10 单位分钟
     */
    @ApiModelProperty(value="持续时间 单位分钟", required = true, example = "10")
    private Long hold;
    /**
     * 锁定时间 5 单位分钟
     */
    @ApiModelProperty(value="锁定时间 单位分钟", required = true, example = "5")
    private Long lockTime;
    /**
     * 源币种  EOS
     */
    @ApiModelProperty(value="源币种", required = true, example = "btc")
    private String pair;
    /**
     * 参照币种  ETH
     */
    @ApiModelProperty(value="参照币种", required = true, example = "usdt")
    private String base;
    /**
     * 数据源 币安
     */
    @ApiModelProperty(value="数据源", required = true, example = "币安")
    private String datasource;
    /**
     * 中奖范围
     */
    @ApiModelProperty(value="中奖范围", required = true, example = "0.001")
    private Double rewardRatio;

    /**
     * 获取oracle 结果的时间 秒 1,523,621,460
     */
    @ApiModelProperty(value="获取oracle结果的时间 秒", required = true, example = "1538701000")
    private Long getOracleTime;

    /**
     * 类型 币安1 篮球2 足球3
     */
    @ApiModelProperty(value="币安1 篮球2 足球3", required = true, example = "1")
    private Long type;

    public EnumError initActivity(Activity activity) {
        EnumError enumError = checkCreates();
        /*
         * something can't set e.g. OracleId
         */
        if (enumError.getCode() == EnumError.SUCCESS.getCode()) {
            // attributes should be generated

            // 自动生成
            activity.setId(TimeUtil.generateId());
            activity.setTitle(title);
            activity.setStart(start);
            activity.setPond(pond);
            activity.setHold(hold);

            activity.setLockTime(lockTime);
            activity.setPair(pair);
            activity.setBase(base);
            activity.setEnd(start + (hold + lockTime + 2) * 60);
            activity.setDatasource(datasource);
            activity.setRewardRatio(rewardRatio != null ? rewardRatio : 0.001);

            activity.setStatus(1L);
            activity.setIsSettlement(0L);
            activity.setDelayed(0L);
            activity.setResult(0.0);
            activity.setGetOracleTime(getOracleTime);

            activity.setType(type);
            activity.setCreate(TimeUtil.getCurrentSeconds());
            activity.setDelete(0L);
        }

        return enumError;
    }

    public EnumError updateActivity(Activity activity) {
        EnumError enumError = checkUpdate(activity);
        if (canUpdate(enumError)) {
            activity.setTitle(title);
            activity.setStart(start);
            activity.setPond(pond);
            activity.setHold(hold);

            activity.setLockTime(lockTime);
            activity.setPair(pair);
            activity.setBase(base);
            activity.setEnd(start + (hold + lockTime + 2) * 60);
            activity.setDatasource(datasource);
            activity.setRewardRatio(rewardRatio);
            activity.setTitle(title);
            activity.setGetOracleTime(getOracleTime);
            activity.setType(type);
        }

        return enumError;
    }
    public static boolean canUpdate(EnumError enumError) {
        return enumError.equals(EnumError.SUCCESS)
                || enumError.equals(EnumError.ACTIVITY_STATIC_FIELD);
    }
    public static boolean needReCreateOracle(EnumError enumError) {
        return enumError.equals(EnumError.ACTIVITY_STATIC_FIELD);
    }

    private EnumError checkCreates() {
        if (start == null || title == null || pond == null || hold == null || lockTime == null
                || pair == null || base == null || datasource == null || getOracleTime == null) {
            return EnumError.ACTIVITY_NULL_FIELD;
        }
        if (start > getOracleTime) {
            return EnumError.ACTIVITY_END_GT_START;
        }
        return EnumError.SUCCESS;
    }

    private EnumError checkUpdate(Activity oldActivity) {
        if (oldActivity == null) {
            return EnumError.ACTIVITY_NOT_EXIST;
        }
        if (start > getOracleTime) {
            return EnumError.ACTIVITY_END_GT_START;
        }
        // 创建oracle用的字段不能改
        if (type.equals(oldActivity.getType())
                && pair.equals(oldActivity.getPair())
                && base.equals(oldActivity.getBase())
                && start.equals(oldActivity.getStart())
                && hold.equals(oldActivity.getHold())
                && lockTime.equals(oldActivity.getLockTime())
                && getOracleTime.equals(oldActivity.getGetOracleTime())) {
            return EnumError.SUCCESS;
        } else {
            return EnumError.ACTIVITY_STATIC_FIELD;
        }
    }
}
