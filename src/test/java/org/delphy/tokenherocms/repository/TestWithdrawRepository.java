package org.delphy.tokenherocms.repository;

import org.delphy.tokenherocms.TokenherocmsApplication;
import org.delphy.tokenherocms.entity.Withdraw;
import org.delphy.tokenherocms.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokenherocmsApplication.class)
public class TestWithdrawRepository {
    @Autowired
    private IWithdrawRepository withdrawRepository;

    @Test
    public void generateWithdraw() {
        for (int i = 1; i < 100; i++) {
            Withdraw withdraw = new Withdraw();
            withdraw.setName("n" + i);
            withdraw.setId(TimeUtil.generateId());
            withdraw.setUserId(((Integer)i).toString());
            withdraw.setAddress("0x1617b4C6Fd8befEE9A42D24a9aB0A56abf517Efe");
            withdraw.setCount(1.0);
            withdraw.setStatus(0L);
            withdraw.setPhone("13436665547");
            withdraw.setCreate(TimeUtil.getCurrentSeconds());
            withdraw.setDelete(0L);
            withdrawRepository.insert(withdraw);
        }
    }

    @Test
    public void findByNameLikeOrderByIdDesc() {
        Pageable page = new PageRequest(0, 100);
        List<Withdraw> withdrawList = withdrawRepository.findByNameLikeAndAddressLikeOrderByIdDesc("n2", "0x16", page);
        for (Withdraw withdraw: withdrawList) {
            System.out.println(withdraw.getName());
        }
    }
}
