package org.delphy.tokenherocms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Withdraw;
import org.delphy.tokenherocms.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mutouji
 */
@Slf4j
@Api(tags = {"提币申请"}, description = "提币申请接口")
@RestController
public class WithdrawController {
    private WithdrawService withdrawService;

    public WithdrawController(@Autowired WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @GetMapping("/withdraws")
    @ApiOperation(value = "获取提币记录", notes = "page是索引，size是分页大小")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sid",value="token", paramType = "header", dataType="string", required = true),
            @ApiImplicitParam(name="page",value="分页索引", paramType = "query", dataType="int", required = true, defaultValue = "0"),
            @ApiImplicitParam(name="size",value="分页大小", paramType = "query", dataType="int", required = true, defaultValue = "10"),
            @ApiImplicitParam(name="name",value="用户名", paramType = "query", dataType="string"),
            @ApiImplicitParam(name="address",value="用户地址", paramType = "query", dataType="string")
    })
    public RestResult<List<Withdraw>> getWithdraws(Integer page, Integer size, String name, String address) {
        if (page != null && size != null) {
            return this.withdrawService.getWithdraws(page, size, name, address);
        }
        return new RestResult<>(1102, "missing page(index) or size param");
    }

    @ApiOperation("获取总提币数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="name",value="用户名", paramType = "query", dataType="string"),
            @ApiImplicitParam(name="address",value="用户地址", paramType = "query", dataType="string")
    })
    @GetMapping("/withdrawscount")
    public RestResult<Long> getWithdrawsCount(String name, String address) {
        return this.withdrawService.getWithdrawsCount(name, address);
    }

    @ApiOperation("设置打币标记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true),
            @ApiImplicitParam(name="id",value="提币记录的id", paramType = "path", dataType="string")
    })
    @PostMapping("/withdrawmark/{id}")
    public RestResult<Withdraw> setWithdrawMark(@PathVariable String id) {
        return this.withdrawService.setWithdrawMark(id);
    }

    @ApiOperation("获取所有未打币的提币记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "token", paramType = "header", dataType = "string", required = true)
    })
    @GetMapping("/unresolvedwithdraws")
    public RestResult<List<Withdraw>> getUnresolvedWithdraws() {
        return this.withdrawService.getUnresolvedWithdraws();
    }
}
