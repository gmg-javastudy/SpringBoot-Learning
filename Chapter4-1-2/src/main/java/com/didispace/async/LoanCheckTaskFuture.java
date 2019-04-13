package com.didispace.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

/**
 * @author gmg
 * @title: LoanCheckTaskFuture
 * @projectName SpringCloud-Learning
 * @description: TODO
 * @date 2019/4/13 12:49
 */
@Component
public class LoanCheckTaskFuture {

    /**
     * 贷款前的用户信息检查
     * @return
     */
    @Async
    public CompletableFuture getUserInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture( "身份合法");
    }

    /**
     * 贷款前用户的信用信息检查
     * @return
     */
    @Async
    public CompletableFuture  getBankCreditInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture( "银行信用良好");
    }
//另外一个接口不用异步，50毫秒，用多线程，上下文切换耗时都比这个数大了。
}