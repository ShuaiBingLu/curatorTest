package cn.bin2.curatortest.curatortest.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:09 2019/1/10
 * @Modified By:
 */
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZooConfig {
    private String registy;

    public String getRegisty() {
        return registy;
    }

    public void setRegisty(String registy) {
        this.registy = registy;
    }
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                registy,
                60000,
                5000,
                new RetryNTimes(5, 5000));
    }
}
