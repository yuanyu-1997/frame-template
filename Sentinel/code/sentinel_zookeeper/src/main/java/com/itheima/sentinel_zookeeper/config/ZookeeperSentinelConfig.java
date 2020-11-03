package com.itheima.sentinel_zookeeper.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.zookeeper.ZookeeperDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ZookeeperSentinelConfig {

    @PostConstruct
    public void doInit(){
        //连接zookeeper获取规则
        //连接zookeeper的地址
        String remoteAddress = "127.0.0.1:2181";
        //zookeeper中数据的路径
        String path = "/Sentinel/zookeeper";

        //连接zookeeper获取规则
        //参数1：zookeeper的地址
        //参数2：zookeeper中数据的路径
        //参数3：数据的类型
        ReadableDataSource<String, List<FlowRule>> readableDataSource = new ZookeeperDataSource<>(
                remoteAddress,
                path,
                source -> JSONObject.parseObject(source,new TypeReference<List<FlowRule>>(){})
        );

        //加载规则
        FlowRuleManager.register2Property(readableDataSource.getProperty());
    }

}
