package cn.yuanyu.db.service;


import cn.yuanyu.db.pojo.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanyu
 */
@Service
@Transactional
public class BrandService {
    public void insert(Brand brand) {
    }

    public void update(Brand brand) {
    }

    public void delete(Integer id) {
    }

    @RequestMapping("/selectOne")
    public Brand selectOne(Integer id) {
        return new Brand();
    }

    @RequestMapping("/selectList")
    public List<Brand> selectList() {
        return new ArrayList<>();
    }
}
