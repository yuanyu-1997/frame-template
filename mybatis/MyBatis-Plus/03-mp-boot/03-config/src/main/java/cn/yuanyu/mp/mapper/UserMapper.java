package cn.yuanyu.mp.mapper;


import cn.yuanyu.mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author yuanyu
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     *
     */
    User findById(Long id);

}
