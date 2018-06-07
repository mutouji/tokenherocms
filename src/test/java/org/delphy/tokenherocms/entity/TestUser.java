package org.delphy.tokenherocms.entity;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.repository.IUserRepository;
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
        userRepository.save(user);
    }

    @Test
    public void generate100User() {
        Long now = new Date().getTime();
        for (Integer i = 1; i < 101; i++) {
            System.out.println("generate user " + i.toString());
            String idStr = i.toString();
//            userRepository.delete(idStr);
            User user = new User();
            user.setId(idStr);
            user.setName("n" + idStr);
            user.setOpenId("oI3TD5FDvMqnDwhS5u8m-UzmYOnU");
            user.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELXE4u7YibNUPcOciaTC6niaqRSdGl7ex7XvibnQTDCicj3cWoRrxgcg7CC5aRWYicqO7frsMDXArwsZOXw/0");
            user.setCreate(now + i);
            userRepository.save(user);
        }
    }

    @Test
    public void get20_30User() {
        Pageable pageable = new PageRequest(2, 10);
        List<User> users = userRepository.findAllByOrderByCreateDesc(pageable);

        for (User user : users) {
            System.out.println("user id = " + user.getId() + ", name = " + user.getName());
        }
    }
}
