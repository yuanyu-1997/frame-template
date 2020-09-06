package cn.yuanyu.db.service;


import cn.yuanyu.db.pojo.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemService {


    public void insert(Item item) {
    }

    public void update(Item item) {
    }

    public void delete(Integer id) {
    }

    public Item selectOne(Integer id) {
        return new Item();
    }

    public List<Item> selectList() {
        return new ArrayList<>();
    }
}
