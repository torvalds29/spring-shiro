package com.sinosoft.shiro.service;

import com.sinosoft.shiro.model.Permission;
import com.sinosoft.shiro.model.User;

import java.util.List;

/**
 * Created by oracle on 2017-03-19.
 */
public interface AdminService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    public User findUserByusername(String userName);

    void saveAndFlush(User user);

    List<String> getPermissionStrings(User user);

    List<String> getRoleCodes(User user);
}
