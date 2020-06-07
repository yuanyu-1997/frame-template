package com.nobug.service;


import com.nobug.entity.Brand;
import com.nobug.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BrandRepository brandRepository;

    public void insert(Brand brand){
        //brandRepository.save(brand);
    }

    public void update(Brand brand){
        //brandMapper.update(brand);
    }

    public void delete(Integer id){
        //brandMapper.delete(id);
    }

    @RequestMapping("/selectOne")
    public Brand selectOne(Integer id) {
        //Brand brand = brandRepository.selectOne(id);
        //return brand;
        //return new Brand();
        return null;
    }

    @RequestMapping("/selectList")
    public List<Brand> selectList(){
        //List<Brand> brandList = brandMapper.selectList();
        //return brandList;
        return new ArrayList<>();
    }


}
