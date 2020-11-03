package cn.yuanyu.rule.sentinel_rule.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysController {

    //定义资源  EntryType.IN : 入口资源
    @SentinelResource(entryType = EntryType.IN)
    @GetMapping("sys")
    public String hello(){
        //使用限流规则
        return "Hello Sentinel!";
    }


    //定义系统自适应保护规则
    /*@PostConstruct
    public void initDegradeRule(){
        //1.创建存放规则的集合
        List<SystemRule> rules = new ArrayList<>();
        //2.创建系统自适应保护规则
        SystemRule rule = new SystemRule();
        //定义入口资源的QPS规则  参数：QPS每秒允许的最大请求数
        rule.setQps(2);
        //3.将规则保存到集合中
        rules.add(rule);
        //4.加载规则
        SystemRuleManager.loadRules(rules);
    }*/

}
