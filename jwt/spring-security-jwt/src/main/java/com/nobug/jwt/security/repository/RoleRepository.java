package com.nobug.jwt.security.repository;


import com.nobug.jwt.security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yuanyu
 */
public interface RoleRepository extends JpaSpecificationExecutor<RoleEntity>, CrudRepository<RoleEntity, String> {
}
