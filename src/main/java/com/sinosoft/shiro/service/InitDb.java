package com.sinosoft.shiro.service;

import com.sinosoft.shiro.model.Permission;
import com.sinosoft.shiro.model.Role;
import com.sinosoft.shiro.model.User;
import com.sinosoft.shiro.model.repository.UserRope;
import com.sinosoft.shiro.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by oracle on 2017-03-19.
 */
@Component
public class InitDb {
    @Autowired
    UserRope userRope;
    @PostConstruct
    public void initDb() {
        User user = new User();
        user.setUserName("406075869@qq.com");
        user.setSalt(new String(EncryptUtil.generateSalt(AdminService.SALT_SIZE)));
        user.setPassword(EncryptUtil.enryptPassword("123456", user.getSalt()));
        Permission permission = new Permission();
        permission.setUrl("edict");
        Permission permission1 = new Permission();
        permission1.setUrl("guanliyuan:read");
        Role role = new Role();
        role.setPermissions(Arrays.asList(permission, permission1));
        role.setRoleCode("guanliyuan");
        role.setRoleName("管理员");
        user.setRole(Arrays.asList(role));
        User user1=new User();
        user1.setUserName("oracle@qq.com");
        user1.setSalt(new String(EncryptUtil.generateSalt(AdminService.SALT_SIZE)));
        user1.setPassword(EncryptUtil.enryptPassword("123456", user1.getSalt()));
        user1.setRole(Arrays.asList(new Role()));
        userRope.save(Arrays.asList(user,user1));
    }

}
