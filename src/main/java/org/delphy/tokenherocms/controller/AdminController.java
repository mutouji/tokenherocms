package org.delphy.tokenherocms.controller;

import io.swagger.annotations.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.annotation.RequestLimit;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.domain.AdminUserManager;
import org.delphy.tokenherocms.pojo.AdminCache;
import org.delphy.tokenherocms.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mutouji
 */
@Slf4j
@Api(value="admin", tags={"登陆"}, description = "管理员接口")
@RestController
public class AdminController {
    private AdminUserManager adminUserManager;

    public AdminController(@Autowired AdminUserManager adminUserManager) {
        this.adminUserManager = adminUserManager;
    }

    @ApiOperation(value="管理员登陆")
    @PostMapping("/login")
    @RequestLimit(count=10)
    public RestResult<String> login(@RequestBody SignInRequest signInRequest, HttpServletRequest request) {
        RequestUtil.logRequestIpAndUrl(request);
        if (signInRequest == null) {
            return new RestResult<>(1101, "param wrong format");
        }
        return adminUserManager.login(signInRequest.getAccount(), signInRequest.getPassword());
    }

    @ApiOperation(value="获取admin")
    @GetMapping("/login")
    public RestResult<AdminCache> getAdmin(@RequestHeader String sid) {
        return adminUserManager.getAdmin(sid);
    }

    @ApiModel(description = "登陆时，需要传递过来的实体")
    @Data
    static class SignInRequest {
        @ApiModelProperty(value="password", required = true, example = "Dpy666888666Dpy")
        private final String password = null;
        @ApiModelProperty(value="account", required = true, example = "admin")
        private final String account = null;
    }
}
