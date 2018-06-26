package com.peng1m.education.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @RestResource
    public long getId() {
        return roleId;
    }
}
