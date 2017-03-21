package com.sinosoft.shiro.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oracle on 2017-03-19.
 */
@Entity
public class Role {
    @Id
    @GenericGenerator(name = "PKUUID", strategy = "uuid2")
    @GeneratedValue(generator = "PKUUID")
    private String id;
    private String roleCode;
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.PERSIST)
    @JoinTable(name = "user_role_links", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "role_user_foreignKey"), inverseForeignKey = @ForeignKey(name = "user_role_foreignKey"))
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.PERSIST)
    @JoinTable(name = "role_permission", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}, foreignKey = @ForeignKey(name = "role_permission_fk"), inverseForeignKey = @ForeignKey(name = "permission_role_fk"))
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role roles = (Role) o;

        return id != null ? id.equals(roles.id) : roles.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
