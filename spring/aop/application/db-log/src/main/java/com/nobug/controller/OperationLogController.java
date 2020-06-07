package com.nobug.controller;


import com.nobug.service.OperationLogService;
import com.nobug.vo.OperationLogParameter;
import com.nobug.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * @param pageNum 从那一页开始
     * @param rows    每页返回多少条记录
     */
    @GetMapping("/findByPage")
    public PageResult findByPage(OperationLogParameter opParam, Integer pageNum, Integer rows) {
        pageNum -= 1;
        return operationLogService.findByPage(opParam, pageNum, rows);
    }

}
