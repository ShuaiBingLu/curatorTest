package cn.bin2.curatortest.curatortest.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:20 2019/1/10
 * @Modified By:
 */
@Component
public class ZooClient {

    private String url;
    private static final int SLEEP_TIME_MS = 1000;
    private static final int MAX_RETRIES = 3;



    @Autowired
    private ZooConfig zooConfig;

    public CuratorFramework getClient() {
        return client;
    }

    public void setClient(CuratorFramework client) {
        this.client = client;
    }
    @Autowired
    private CuratorFramework client;

    public <T> T lock(AbstractLock<T> mutex) {
        String path = mutex.getLockPath();
        InterProcessMutex lock = new InterProcessMutex(this.getClient(), path);
        boolean success = false;
        try {
            try {
                success = lock.acquire(mutex.getTimeout(), mutex.getTimeUnit());
            } catch (Exception e) {
                throw new RuntimeException("obtain lock error " + e.getMessage() + ", path " + path);
            }
            if (success) {
                return (T) mutex.execute();
            } else {
                return null;
            }
        } finally {
            try {
                if (success){
                    lock.release();
                }
            } catch (Exception e) {

            }
        }
    }

    public void destroy() {
        try {
            if (getClient() != null) {
                getClient().close();
            }
        } catch (Exception e) {

        }
    }


}
