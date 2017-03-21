package com.sinosoft.shiro.model.repository;

import com.sinosoft.shiro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oracle on 2017-03-19.
 */
public interface RoleRepo extends JpaRepository<Role,String> {
}
