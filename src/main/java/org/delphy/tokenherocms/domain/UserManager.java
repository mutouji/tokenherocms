package org.delphy.tokenherocms.domain;

import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.User;
import org.delphy.tokenherocms.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.List;

/**
 * @author mutouji
 */
@Service
public class UserManager {
    private IUserRepository userRepository;

    public UserManager(@Autowired IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public RestResult<List<User>> getUsers(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        List<User> users = this.userRepository.findAllByOrderByCreateDesc(pageable);
        return new RestResult<>(0, Constant.SUCCESS, users);
    }

    public RestResult<Long> getUsersCount() {
        Long count = this.userRepository.count();
        return new RestResult<>(0, Constant.SUCCESS, count);
    }
}
