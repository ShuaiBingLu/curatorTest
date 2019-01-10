package cn.bin2.curatortest.curatortest.config;

/**
 * @Author: bingshuai.lu
 * @Description:
 * @Date: Created in 9:41 2019/1/10
 * @Modified By:
 */
public class ZooLock extends AbstractLock<String> {

    private static final  String LOCK_PATH="/zookeeper/quota/test_";

    private String lockId;
    public ZooLock(String lockId){
        this.lockId = lockId;
    }

    @Override
    public String getLockPath() {
        return LOCK_PATH+lockId;
    }

    @Override
    public String execute() {
        return "test";
    }


}
