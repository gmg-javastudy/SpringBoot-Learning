package com.didispace.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * @author gmg
 * @title: MyTestService
 * @projectName SpringCloud-Learning
 * @description: TODO
 * @date 2019/4/13 12:40
 */
@Service
public class MyTestService {
    @Autowired
    LoanCheckTask loanCheckTask;

    @Autowired
    LoanCheckTaskFuture loanCheckTaskFuture;

    /**
     * 开通贷款服务
     * @return
     */
    public boolean openLoanService(){
        long s = System.currentTimeMillis();
        //获取用户基本信息
        String userInfo = loanCheckTask.getUserInfo();//1
        //获取用户的信用信息
        String bankCreditInfo = loanCheckTask.getBankCreditInfo();//2
        //是否可以开通贷款服务
        Boolean allowLoan = loanCheckTask.getAllowLoan(userInfo,bankCreditInfo);//3
        return allowLoan;
    }

    public boolean openLoanService1(){
        CompletableFuture<Boolean> cf = CompletableFuture
                .supplyAsync(() -> loanCheckTask.getUserInfo())//s1
                .thenCombineAsync(CompletableFuture.supplyAsync(()->loanCheckTask.getBankCreditInfo()),//s2
                        (BiFunction<String, String, Boolean>) (s12, s2) -> loanCheckTask.getAllowLoan(s12,s2));//s3
        boolean allowLoan = cf.join();
        return allowLoan;
    }

    public boolean openLoanService2() {
        long s = System.currentTimeMillis();
        CompletableFuture userInfoFuture = loanCheckTaskFuture.getUserInfo();
        CompletableFuture bankCreditInfoFuture = loanCheckTaskFuture.getBankCreditInfo();
        CompletableFuture<Boolean> future = userInfoFuture.thenCombine(bankCreditInfoFuture,
                (BiFunction<String, String, Boolean>) (x, y) -> loanCheckTask.getAllowLoan(x, y));
        Boolean join = future.join();
        return join;
    }


}
