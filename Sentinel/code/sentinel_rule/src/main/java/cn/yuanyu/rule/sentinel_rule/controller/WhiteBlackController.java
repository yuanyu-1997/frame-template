package cn.yuanyu.rule.sentinel_rule.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhiteBlackController {

    //定义资源  value:设置资源的名称   blockHandler：设置限流或降级的处理函数
    @SentinelResource(value = "Sentinel_Rule",blockHandler = "exceptionHandler")
    @GetMapping("origin")
    public String hello(){
        //使用限流规则
        return "Hello Sentinel!";
    }

    //被限流或降级的处理函数
    public String exceptionHandler(BlockException e){
        e.printStackTrace();
        return "系统繁忙，请稍候";
    }

    //定义授权控制规则  白名单
    /*@PostConstruct
    public void initWhiteRule(){
        //1.创建存放规则的集合
        List<AuthorityRule> rules = new ArrayList<>();
        //2.创建授权控制规则
        AuthorityRule rule = new AuthorityRule();
        //定义资源名称
        rule.setResource("Sentinel_Rule");
        //定义限制模式  白名单:RuleConstant.AUTHORITY_WHITE
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        //定义请求来源
        rule.setLimitApp("192.168.0.109");
        //3.将规则保存到集合中
        rules.add(rule);
        //4.加载规则
        AuthorityRuleManager.loadRules(rules);
    }*/


    //定义授权控制规则  黑名单
    /*@PostConstruct
    public void initBlackRule(){
        //1.创建存放规则的集合
        List<AuthorityRule> rules = new ArrayList<>();
        //2.创建授权控制规则
        AuthorityRule rule = new AuthorityRule();
        //定义资源名称
        rule.setResource("Sentinel_Rule");
        //定义限制模式  黑名单:RuleConstant.AUTHORITY_BLACK
        rule.setStrategy(RuleConstant.AUTHORITY_BLACK);
        //定义请求来源
        rule.setLimitApp("127.0.0.1");
        //3.将规则保存到集合中
        rules.add(rule);
        //4.加载规则
        AuthorityRuleManager.loadRules(rules);
    }*/
}
