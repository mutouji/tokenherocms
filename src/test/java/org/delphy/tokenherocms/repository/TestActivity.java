package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.Activity;
import org.delphy.tokenherocms.pojo.ActivityVo;
import org.delphy.tokenherocms.service.ActivityService;
import org.delphy.tokenherocms.util.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestActivity {
    @Autowired
    private IActivityRepository iActivityRepository;

    @Autowired
    private ActivityService activityService;

    @Before
    public void setUp() {

    }

    @After
    public void endUp() {

    }

    @Test
    public void testSave() {
        long time = TimeUtil.getCurrentSeconds() / 60;
        time = time * 60;

        Random random = new Random();
        for(int i = 0; i < 3; i ++) {
            ActivityVo vo = new ActivityVo();
            vo.setBase("USDT");
            vo.setDatasource("币安");
            vo.setHold(3L);
            vo.setLockTime(1L);
            vo.setGetOracleTime(time + i * (vo.getHold() + vo.getLockTime() + 3) * 60 + (vo.getHold() + vo.getLockTime() + 1) * 60);
            vo.setPair("BTC");
            vo.setPond(400L);
            vo.setStart(time + i * (vo.getHold() + vo.getLockTime() + 3) * 60);
            vo.setRewardRatio(0.001);
            vo.setTitle("预测BTC在20点10分的收盘价是多少？允许误差范围0.1%");
            vo.setType(1L);
            activityService.createActivity(vo, 1L);
        }
    }
}
