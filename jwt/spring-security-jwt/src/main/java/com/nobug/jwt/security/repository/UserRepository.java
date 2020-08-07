package com.nobug.jwt.security.repository;


import com.nobug.jwt.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yuanyu
 */
public interface UserRepository extends JpaSpecificationExecutor<UserEntity>, CrudRepository<UserEntity, String> {
    /**
     * 根据账号查询用户信息
     */
    UserEntity findByUsername(String username);
}
