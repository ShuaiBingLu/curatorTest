package cn.bin2.curatortest.curatortest.config;

import java.util.concurrent.TimeUnit;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:26 2019/1/10
 * @Modified By:
 */
public abstract class AbstractLock<T> {


    private static final int TIME_OUT = 5;

    public abstract String getLockPath();

    public abstract T execute();

    public int getTimeout(){
        return TIME_OUT;
    }

    public TimeUnit getTimeUnit(){
        return TimeUnit.SECONDS;
    }


}
