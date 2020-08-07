package com.nobug.jwt.security.repository;


import com.nobug.jwt.security.entity.PermissionEntity;
import com.nobug.jwt.security.entity.RoleEntity;
import com.nobug.jwt.security.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 获取加密密码
     *
     * @param password 原始密码
     * @return 加密后的密码
     */
    private String getHashpw(String password) {
        return passwordEncoder.encode(password);
    }


    /**
     * 添加权限
     */
    public void addPermission() {
        permissionRepository.save(new PermissionEntity("1", "p1", "测试资源1", "/r/r1"));
        permissionRepository.save(new PermissionEntity("2", "p2", "测试资源2", "/r/r2"));
    }

    /**
     * 添加角色
     */
    public void addRole() {
        RoleEntity admin = new RoleEntity("1", "管理员", null, null);
        admin.getPermissions().add(permissionRepository.findById("1").get());
        admin.getPermissions().add(permissionRepository.findById("2").get());
        roleRepository.save(admin);

        //
        RoleEntity simple = new RoleEntity("2", "simple", null, null);
        //只能访问资源1
        simple.getPermissions().add(permissionRepository.findById("1").get());
        roleRepository.save(simple);
    }

    public void addUser() {
        //超级原理员
        UserEntity lisi = new UserEntity("1", "lisi", getHashpw("123456"), "李四", "17783649163");
        lisi.getRoles().add(roleRepository.findById("1").get());
        userRepository.save(lisi);

        //普通员工
        UserEntity zhangsan = new UserEntity("2", "zhangsan", getHashpw("123456"), "张三", "17783649183");
        zhangsan.getRoles().add(roleRepository.findById("2").get());
        userRepository.save(zhangsan);
    }

    @Test
    public void testAdd() {
        addPermission();
        addRole();
        addUser();
    }

    @Test
    public void testFind() {
        UserEntity byId = userRepository.findById("1").get();
        UserEntity byName = userRepository.findByUsername("lisi");

        UserEntity zhangsan = userRepository.findByUsername("zhangsan");
    }
}