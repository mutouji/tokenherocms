package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestActivity {
    @Autowired
    private IActivityRepository iActivityRepository;

    @Before
    public void setUp() {

    }

    @After
    public void endUp() {

    }

    @Test
    public void testSave() {
        Activity activity = new Activity();
        activity.setId("15236027302838709");
        activity.setTitle("预测EOS在20点10分的收盘价是多少？允许误差范围0.1%");
        activity.setStart(1523620500L);
        activity.setPond(400L);
        activity.setHold(10L);
        activity.setLockTime(5L);
        activity.setPair("EOS");
        activity.setBase("ETH");
        activity.setEnd(1523621520L);
        activity.setDatasource("币安");
        activity.setStatus(5L);
        activity.setIsSettlement(1L);
        activity.setDelayed(0L);
        activity.setResult(0.01805);
        activity.setGetOracleTime(1523621460L);
        activity.setOracleId(102L);
        activity.setType(1L);
        activity.setCreate(1523602730L);
        activity.setDelete(0L);
        iActivityRepository.save(activity);
    }
}
