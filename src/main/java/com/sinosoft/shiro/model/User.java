package com.sinosoft.shiro.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by oracle on 2017-03-19.
 */
@Entity(name = "admin_user")
public class User {

    @Id
    @GenericGenerator(name = "PKUUID", strategy = "uuid2")
    @GeneratedValue(generator = "PKUUID")
    private String id;
    private String userName;
    private String password;
    private String salt;
    private Date lastLogin;
    private String LastLoginHost;
    @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.PERSIST)
    @JoinTable(name = "user_role_links", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "user_role_foreignKey"), inverseForeignKey = @ForeignKey(name = "role_user_foreignKey"))
    private List<Role> role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLoginHost() {
        return LastLoginHost;
    }

    public void setLastLoginHost(String lastLoginHost) {
        LastLoginHost = lastLoginHost;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
