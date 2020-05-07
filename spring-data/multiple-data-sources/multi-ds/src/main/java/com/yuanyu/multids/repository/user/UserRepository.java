package com.yuanyu.multids.repository.user;


import com.yuanyu.multids.domain.user.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yuanyu
 */
public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {
    /**
     *
     * @param name 用户名
     * @return 根据用户名查询用户
     */
    User findByName(String name);
}
