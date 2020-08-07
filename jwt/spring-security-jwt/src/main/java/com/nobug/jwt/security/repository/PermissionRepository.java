package com.nobug.jwt.security.repository;


import com.nobug.jwt.security.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author yuanyu
 */
public interface PermissionRepository extends JpaSpecificationExecutor<PermissionEntity>, CrudRepository<PermissionEntity, String> {

}
