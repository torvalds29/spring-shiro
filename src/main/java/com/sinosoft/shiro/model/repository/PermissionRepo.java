package com.sinosoft.shiro.model.repository;

import com.sinosoft.shiro.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oracle on 2017-03-19.
 */
public interface PermissionRepo extends JpaRepository<Permission,String> {
}
