package cn.yuanyu.db.controller;


import cn.yuanyu.db.service.OperationLogService;
import cn.yuanyu.db.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;




    /**
     * @param paramMap 查询条件
     * @param pageNum  查询的页码
     * @param rows     每页查询的条数
     */
    // http://localhost:5000/operationLog/findByPage?pageNum=1&rows=10
    // {"operateUser":"","operateMethod":"","returnClass":"","costTime":"311"}
    // TOOD 因为layui的原因，这里先 @RequestParam Map<String, Object> paramMap
    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestParam Map<String, Object> paramMap, Integer pageNum, Integer rows) {
        return operationLogService.findByPage(paramMap, pageNum, rows);
    }

}
