package com.itheima.sentinel_zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SentinelZookeeperApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        //zookeeper的服务端连接地址
        final String remoteAddress = "127.0.0.1:2181";

        //发送的规则
        //resource : 资源名
        //controlBehavior：流控效果
        //count：阀值
        //grade：规则类型
        //limitApp：调用来源
        //strategy：判断根据是资源自身，还是根据其他关联资源，还是根据链路入口
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"Sentinel_Zookeeper\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 2.0,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";
        //创建连接zookeeper
        CuratorFramework zkClient = CuratorFrameworkFactory.newClient(remoteAddress, new ExponentialBackoffRetry
                (1000, 3));
        //开始连接
        zkClient.start();
        //配置zookeeper数据路径
        String path = "/Sentinel/zookeeper";
        Stat stat = zkClient.checkExists().forPath(path);
        //发送数据给zookeeper
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        zkClient.setData().forPath(path, rule.getBytes());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //关闭连接
        zkClient.close();

    }

}
