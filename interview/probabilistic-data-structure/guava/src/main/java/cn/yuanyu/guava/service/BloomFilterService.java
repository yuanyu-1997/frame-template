package cn.yuanyu.guava.service;


import cn.yuanyu.guava.domain.SysUser;
import cn.yuanyu.guava.mapper.SysUserMapper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class BloomFilterService {

    @Resource
    private SysUserMapper sysUserMapper;

    // 布隆过滤器
    private BloomFilter<Integer> bf;

    @PostConstruct // 程序启动时加载此方法
    public void initBloomFilter() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return;
        }
        // 初始化布隆过滤器
        bf = BloomFilter.create(Funnels.integerFunnel(), sysUsers.size());
        for (SysUser sysUser : sysUsers) {
            bf.put(sysUser.getId());
        }
    }

    /**
     * 判断id可能存在布隆过滤器里面
     */
    public boolean userIdExists(int id) {
        return bf.mightContain(id);
    }
}
