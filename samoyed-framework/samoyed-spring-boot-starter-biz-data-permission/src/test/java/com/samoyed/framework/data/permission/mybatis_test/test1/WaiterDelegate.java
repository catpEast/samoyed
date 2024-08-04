package com.samoyed.framework.data.permission.mybatis_test.test1;

public class WaiterDelegate {

    private Waiter waiter;

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    /**
     * 
     * 
     * @Title: service
     * 
     * @Description: waiter类中方法的调用，通过该方法发起
     * 
     * @param name
     * 
     * @return: void
     */
    public void service(String name) {
        waiter.greetTo(name);
        waiter.serverTo(name);
    }

}