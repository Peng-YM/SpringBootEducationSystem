package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Table(name = "credentials")
@ToString
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "credential_id")
    private Long credentialId;

    @Column(unique = true, nullable = false) // username is unique and not null
    private String username;

    @JsonIgnore  // ignore password in returned JSON
    @Size(min = 6) // password should not short than 6 characters
    @Column(nullable = false) // not null
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "credential_roles",
            joinColumns = @JoinColumn(
                    name = "credential_id", referencedColumnName = "credential_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id")
    )
    private Collection<Role> roles;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Credential() {
    }

    public Credential(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Transactional
    public Collection<Role> getRoles() {
        return roles;
    }

    @Transactional
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Transactional
    public User getUser() {
        return user;
    }

    @Transactional
    public void setUser(User user) {
        this.user = user;
    }
}
