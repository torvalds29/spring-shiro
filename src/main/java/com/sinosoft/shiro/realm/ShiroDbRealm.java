package com.sinosoft.shiro.realm;

import com.sinosoft.shiro.model.Permission;
import com.sinosoft.shiro.model.Role;
import com.sinosoft.shiro.model.User;
import com.sinosoft.shiro.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by oracle on 2017-03-19.
 */
@Component

public class ShiroDbRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;

    public ShiroDbRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AdminService.HASH_ALGORITHM);
        matcher.setHashIterations(AdminService.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 认证资源回调函数
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = adminService.findUserByusername(userName);
        List<String> roleCodes = adminService.getRoleCodes(user);
        info.addRoles(roleCodes);
        List<String> permissions = adminService.getPermissionStrings(user);
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 登陆认证回掉函数
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        if (username == null || !StringUtils.hasText(username)) {
            throw new UnsupportedTokenException();
        } else if (password == null || !StringUtils.hasText(new String(password))) {
            throw new CredentialsException();
        }
        User user = adminService.findUserByusername(username);
        if (user != null) {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            user.setLastLogin(new Date());
            user.setLastLoginHost(session.getHost());
            adminService.saveAndFlush(user);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
            return info;
        } else {
            throw new UnknownAccountException();
        }
    }
}
