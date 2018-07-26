package org.delphy.tokenherocms.service;

import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.RestResult;
import org.delphy.tokenherocms.entity.Withdraw;
import org.delphy.tokenherocms.repository.IWithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mutouji
 */
@Service
public class WithdrawService {
    private IWithdrawRepository withdrawRepository;

    public WithdrawService(@Autowired IWithdrawRepository withdrawRepository) {
        this.withdrawRepository = withdrawRepository;
    }

    public RestResult<List<Withdraw>> getWithdraws(Integer page, Integer size, String name, String address) {
        Pageable pageable = new PageRequest(page, size);
        List<Withdraw> withdraws;
        if (name == null || name.isEmpty()) {
            if (address == null || address.isEmpty()) {
                withdraws = this.withdrawRepository.findAllByOrderByIdDesc(pageable);
            } else {
                withdraws = this.withdrawRepository.findByAddressLikeOrderByIdDesc(address, pageable);
            }
        } else {
            if (address == null || address.isEmpty()) {
                withdraws = this.withdrawRepository.findByNameLikeOrderByIdDesc(name, pageable);
            } else {
                withdraws = this.withdrawRepository.findByNameLikeAndAddressLikeOrderByIdDesc(name, address, pageable);
            }
        }
        return new RestResult<>(0, Constant.SUCCESS, withdraws);
    }

    public RestResult<Long> getWithdrawsCount(String name, String address) {
        Long count;
        if (name == null || name.isEmpty()) {
            if (address == null || address.isEmpty()) {
                count = this.withdrawRepository.count();
            } else {
                count = this.withdrawRepository.countByAddressLike(address);
            }
        } else {
            if (address == null || address.isEmpty()) {
                count = this.withdrawRepository.countByNameLike(name);
            } else {
                count = this.withdrawRepository.countByNameLikeAndAddressLike(name, address);
            }
        }
        return new RestResult<>(0, Constant.SUCCESS, count);
    }

    public RestResult<Withdraw> setWithdrawMark(String id) {
        Withdraw withdraw = this.withdrawRepository.findOne(id);
        if (withdraw.getStatus() == 1) {
            return new RestResult<>(0, Constant.SUCCESS, withdraw);
        } else {
            withdraw.setStatus(1 - withdraw.getStatus());
            this.withdrawRepository.save(withdraw);
            return new RestResult<>(0, Constant.SUCCESS, withdraw);
        }
    }

    public RestResult<List<Withdraw>> getUnresolvedWithdraws() {
        List<Withdraw> withdraws = this.withdrawRepository.findByStatus(0L);
        return new RestResult<>(0, Constant.SUCCESS, withdraws);
    }
}
