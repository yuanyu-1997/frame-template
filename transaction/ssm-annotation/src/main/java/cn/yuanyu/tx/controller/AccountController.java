package cn.yuanyu.tx.controller;

import cn.yuanyu.tx.entity.Account;
import cn.yuanyu.tx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private AccountService accountService;


    /**
     * http://localhost:8080/user/info?name=张三
     */
    @GetMapping("/info")
    public Account findAccountByName(@RequestParam String name) {
        return accountService.findAccountByName(name);
    }

    //accountService.transfer("张三","李四",100f)

    /**
     * http://localhost:8080/user/transfer?sourceName=张三&targetName=李四&money=100
     */
    @PostMapping("/transfer")
    public void transfer(@RequestParam String sourceName, @RequestParam String targetName, @RequestParam Float money) {
        accountService.transfer(sourceName, targetName, money);
    }

}
