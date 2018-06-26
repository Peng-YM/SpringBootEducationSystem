package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    private String avatar;
    private String firstName;
    private String lastName;
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    )
    private Collection<Course> courses;

    public User(@NotNull @Email String email, @NotNull @Size(min = 3, max = 12) String password, String avatar, String firstName, String lastName, String phone, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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
    public Collection<Course> getCourses() {
        return courses;
    }

    @Transactional
    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    @RestResource
    public long getId() {
        return userId;
    }
}
