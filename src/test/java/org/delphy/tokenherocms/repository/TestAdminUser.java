package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.AdminUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestAdminUser {
    @Autowired
    IAdminUserRepository iAdminUserRepository;

    @Before
    public void setUp() {

    }

    @After
    public void endUp() {

    }

    @Test
    public void testSaveAdmin() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("admin");
        adminUser.setId("1");
        adminUser.setPwd("RHB5NjY2ODg4NjY2RHB5");
        iAdminUserRepository.save(adminUser);
    }

    @Test
    public void testGetAdmin() {
        AdminUser adminUser = iAdminUserRepository.findOne("1");
        if (adminUser != null) {
            System.out.println("id = "+ adminUser.getId() + ", account = " + adminUser.getAccount() + ", password = " + adminUser.getPwd());
        } else {
            System.out.println("error: user not exist");
        }
    }

    @Test
    public void testGetAdminByAccount() {
        AdminUser adminUser = iAdminUserRepository.findOneByAccount("admin");
        if (adminUser != null) {
            System.out.println("id = "+ adminUser.getId() + ", account = " + adminUser.getAccount() + ", password = " + adminUser.getPwd());
        } else {
            System.out.println("error: user not exist");
        }
    }
}
