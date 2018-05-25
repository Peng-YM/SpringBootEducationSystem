package com.peng1m.education.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    public Role(){}

    public Role(String name) {
        this.name = name;
    }

    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
