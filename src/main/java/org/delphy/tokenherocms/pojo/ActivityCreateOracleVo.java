package org.delphy.tokenherocms.pojo;

import lombok.Data;
import org.delphy.tokenherocms.entity.Activity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mutouji
 */
@Data
public class ActivityCreateOracleVo {
    private Long type;
    private String ticker;
    private Long endTime;
    private Long disputeEndTime;
    private List<String> news;
    private List<String> resultValues;
    private List<String> scenarios;

    public void initFromActivity(Activity activity) {
        this.setType(activity.getType());
        this.setEndTime(activity.getGetOracleTime());
        this.setDisputeEndTime(activity.getStart() + (activity.getHold() + activity.getLockTime() + 2) * 60);
        this.setTicker(activity.getPair() + activity.getBase());
        this.setNewsFromActivity(activity);
        this.setResultValuesFromActivity(activity);
        this.setScenariosFromActivity(activity);
    }

    private void setNewsFromActivity(Activity activity) {
        String priceClose = "priceclose(now)";

        List<String> news = new ArrayList<>(3);
        news.add(priceClose);

        this.setNews(news);
    }

    private void setResultValuesFromActivity(Activity activity) {
        String priceClose = MessageFormat.format("priceclose({0})", activity.getEnd().toString());

        List<String> resultValues = new ArrayList<>(1);
        resultValues.add(priceClose);

        this.setResultValues(resultValues);
    }

    private void setScenariosFromActivity(Activity activity) {
//        String ge = MessageFormat.format("pricechange({0},{1})>=0", activity.getStart().toString(), activity.getEnd().toString());
//        String lt = MessageFormat.format("pricechange({0},{1})<0", activity.getStart().toString(), activity.getEnd().toString());
//
//        List<String> scenarios = new ArrayList<>(2);
//        scenarios.add(ge);
//        scenarios.add(lt);

        List<String> scenarios = new ArrayList<>(1);
        scenarios.add(Boolean.TRUE.toString());


        this.setScenarios(scenarios);
    }
}
