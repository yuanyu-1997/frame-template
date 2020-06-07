package com.nobug.controller;


import com.nobug.aop.OperateLog;
import com.nobug.entity.Brand;
import com.nobug.service.BrandService;
import com.nobug.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanyu
 */
@Slf4j
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @OperateLog
    @PostMapping("")
    public Result insert(@RequestBody Brand brand) {
        try {
            brandService.insert(brand);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false, "操作失败");
        }
    }

    @OperateLog
    @PutMapping("")
    public Result update(@RequestBody Brand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false, "操作失败");
        }
    }


    @OperateLog
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        try {
            brandService.delete(id);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false, "操作失败");
        }
    }

    @OperateLog
    @GetMapping("/{id}")
    public Brand selectOne(@PathVariable("id") Integer id) {
        return brandService.selectOne(id);
    }

    @OperateLog
    @GetMapping("/")
    public List<Brand> selectList() {
        return brandService.selectList();
    }
}
