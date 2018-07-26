package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.Forecast;
import org.delphy.tokenherocms.util.TimeUtil;
import org.delphy.tokenherocms.entity.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestForecast {
    @Autowired
    private IForecastRepository forecastRepository;

    @Test
    public void generateForecast() {
        for (int i = 0; i < 100; i++) {
            Forecast forecast = new Forecast();
            forecast.setUserId(((Integer)i).toString());
            if (i%2 == 0) {
                forecast.setActivityId("15287821967463961");
            } else {
                forecast.setActivityId("15236027302838709");
            }
            forecast.setFreeNum(2L);
            forecast.setReward(0.58);
            forecast.setRewardTime(TimeUtil.getCurrentSeconds());
            forecast.setRewardRatio(0.005);
            long time = TimeUtil.getCurrentSeconds().intValue() + i;
            Price price = new Price(396.32, 45.0, time);
            List<Price> prices = new ArrayList<>(1);
            prices.add(0,price);
            forecast.setPrices(prices);
            forecast.setLast(price);
            forecast.setCreate(TimeUtil.getCurrentSeconds());
            forecast.setDelete(0L);
            forecastRepository.insert(forecast);
        }
    }
}
