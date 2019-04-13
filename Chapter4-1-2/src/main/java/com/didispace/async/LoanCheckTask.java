package com.didispace.async;

import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

/**
 * @author gmg
 * @title: LoanCheckTask
 * @projectName SpringCloud-Learning
 * @description: TODO
 * @date 2019/4/13 12:39
 */
@Component
public class LoanCheckTask {

    /**
     * 贷款前的用户信息检查
     * @return
     */
    public String  getUserInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "身份合法";
    }

    /**
     * 贷款前用户的信用信息检查
     * @return
     */
    public String  getBankCreditInfo(){
        try {
            sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "银行信用良好";
    }

    /**
     * 开启该用户的贷款权限
     * @return
     */
    public Boolean  getAllowLoan(String userInfo,String creditInfo){
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

}
