package cn.yuanyu.repository;

import cn.yuanyu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yuanyu
 */
public interface UserRepository extends JpaRepository<User, String> {

}