package org.delphy.tokenherocms.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.entity.Forecast;
import org.delphy.tokenherocms.entity.User;

/**
 * @author mutouji
 */
@ApiModel(description = "预测详情")
@Data
public class ForecastVo {
    @ApiModelProperty(value="预测内容", required = true, example = "请预测btc价格")
    private String title;
    @ApiModelProperty(value="预测价格", required = true, example = "6000.0")
    private Double price;
    @ApiModelProperty(value="结果价格", required = true, example = "6001.0")
    private Double result;
    @ApiModelProperty(value="最后一次修改比例", required = true, example = "0.88")
    private Double rewardRatio;
    @ApiModelProperty(value="获奖金额", required = true, example = "10.5")
    private Double reward;

    public ForecastVo(Forecast forecast, Activity activity, User user) {
        title = activity.getTitle();
        price = (forecast == null || forecast.getLast() == null) ? 0.0 : forecast.getLast().getPrice();
        result = activity.getResult();
        rewardRatio = (forecast == null || forecast.getLast() == null) ? 0.0 : forecast.getLast().getRate();
        reward = forecast == null ? 0.0 : forecast.getReward();
    }
}
