package com.sinosoft.shiro.service.impl;

import com.sinosoft.shiro.model.Permission;
import com.sinosoft.shiro.model.Role;
import com.sinosoft.shiro.model.User;
import com.sinosoft.shiro.model.repository.PermissionRepo;
import com.sinosoft.shiro.model.repository.RoleRepo;
import com.sinosoft.shiro.model.repository.UserRope;
import com.sinosoft.shiro.service.AdminService;
import com.sinosoft.shiro.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by oracle on 2017-03-19.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRope userRope;
    @Autowired
    PermissionRepo permissionRepo;

    @Override
    public User findUserByusername(String userName) {
        return userRope.findByUserName(userName);
    }


    @Override
    public void saveAndFlush(User user) {
        userRope.saveAndFlush(user);
    }

    @Override
    public List<String> getPermissionStrings(User user) {
        List<Role> roles = user.getRole();
        Set<String> permissionSet = new HashSet<String>();
        List<String> permissions = new ArrayList<String>();
        for (Role role : roles) {
            for (Permission permission : role.getPermissions()) {
                permissionSet.add(permission.getUrl());
            }
        }
        permissions.addAll(permissionSet);
        return permissions;
    }

    @Override
    public List<String> getRoleCodes(User user) {
        List<String> roleCodes=new ArrayList<String>();
        for (Role role : user.getRole()) {
            roleCodes.add(role.getRoleCode());
        }
        return roleCodes;
    }


}
