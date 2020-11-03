package cn.yuanyu.rule.sentinel_rule.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DegradeController {

    //定义资源  value:设置资源的名称   blockHandler：设置限流或降级的处理函数
    @SentinelResource(value = "Sentinel_Rule",blockHandler = "exceptionHandler")
    @GetMapping("ann")
    public String hello(){
        //使用限流规则
        return "Hello Sentinel!";
    }

    //被限流或降级的处理函数
    public String exceptionHandler(BlockException e){
        e.printStackTrace();
        return "系统繁忙，请稍候";
    }

    //定义熔断降级规则
    /*@PostConstruct
    public void initDegradeRule(){
        //1.创建存放规则的集合
        List<DegradeRule> rules = new ArrayList<>();
        //2.创建熔断降级规则
        DegradeRule rule = new DegradeRule();
        //定义资源名称
        rule.setResource("Sentinel_Rule");
        //定义规则类型  RuleConstant.DEGRADE_GRADE_RT:秒级RT 平均响应时间类型
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //定义阈值
        rule.setCount(0.01);
        //定义降级时间
        rule.setTimeWindow(10);
        //3.将规则保存到集合中
        rules.add(rule);
        //4.加载规则
        DegradeRuleManager.loadRules(rules);
    }*/

}
