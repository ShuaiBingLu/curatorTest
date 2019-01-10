package cn.bin2.curatortest.curatortest;

import cn.bin2.curatortest.curatortest.config.ZooClient;
import cn.bin2.curatortest.curatortest.config.ZooLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuratortestApplicationTests {


    public void contextLoads() {
    }
    @Autowired
    private ZooClient zooClient;
    @Test
    public void test(){
        zooClient.lock(new ZooLock("001"));
    }
}

