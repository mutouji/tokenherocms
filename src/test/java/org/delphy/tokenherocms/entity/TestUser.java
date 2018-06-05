package org.delphy.tokenherocms.entity;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.repository.IUserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        for (Integer i = 0; i < 100; i++) {
            String idStr = i.toString();
            userRepository.delete(idStr);
            User user = new User();
            user.setId(idStr);
            user.setName("n" + idStr);
            user.setOpenId("oI3TD5FDvMqnDwhS5u8m-UzmYOnU");
            user.setAvatar("https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELXE4u7YibNUPcOciaTC6niaqRSdGl7ex7XvibnQTDCicj3cWoRrxgcg7CC5aRWYicqO7frsMDXArwsZOXw/0");
            userRepository.save(user);
        }
    }
}
