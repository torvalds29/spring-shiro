package com.sinosoft.shiro.model.repository;

import com.sinosoft.shiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by oracle on 2017-03-19.
 */
public interface UserRope extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
