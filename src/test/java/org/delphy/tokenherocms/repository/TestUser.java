package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.User;
import org.delphy.tokenherocms.util.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestUser {
    @Autowired
    IUserRepository userRepository;

    @Before
    public void setUp() {

    }

    @After
    public void endUp() {

    }

    @Test
    public void saveOneUser() {
        String id = "15233590196834402";
        userRepository.delete(id);
        User user = new User();
        user.setId(id);
        user.setName("1N");
        user.setOpenId("oI3TD5FDvMqnDwhS5u8m-UzmYOnU");
        user.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELXE4u7YibNUPcOciaTC6niaqRSdGl7ex7XvibnQTDCicj3cWoRrxgcg7CC5aRWYicqO7frsMDXArwsZOXw/0");
        userRepository.insert(user);
    }

    @Test
    public void generate100User() {
        Long now = new Date().getTime();
        for (Integer i = 1; i < 101; i++) {
            System.out.println("generate user " + i.toString());
            double total = TimeUtil.generateTotalDpy();
            double dpy = TimeUtil.generateTotalDpy();
            dpy = dpy > total ? total : dpy;
            String idStr = i.toString();
            User user = new User();
            user.setId(idStr);
            user.setName("n" + idStr);
            user.setOpenId("oI3TD5FDvMqnDwhS5u8m-UzmYOnU");
            user.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELXE4u7YibNUPcOciaTC6niaqRSdGl7ex7XvibnQTDCicj3cWoRrxgcg7CC5aRWYicqO7frsMDXArwsZOXw/0");
            user.setDelete(0L);
            user.setDpy(dpy);
            user.setGender(TimeUtil.generateGender());
            user.setPhone(TimeUtil.generatePhone());
            user.setCreate(now + i);
            user.setTotalDpy(total);
            userRepository.insert(user);
        }
    }

    @Test
    public void get20_30User() {
        Pageable pageable = new PageRequest(2, 10);
        List<User> users = userRepository.findAllByOrderByIdDesc(pageable);

        for (User user : users) {
            System.out.println("user id = " + user.getId() + ", name = " + user.getName());
        }
    }
}
