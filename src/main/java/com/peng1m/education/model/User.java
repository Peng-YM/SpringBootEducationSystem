package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private Credential credential;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    )
    private Collection<Course> courses;

    public User() {
    }

    public User(String email, String firstName, String lastName, String phone, Credential credential) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.credential = credential;
    }

    @Transactional
    public Credential getCredential() {
        return credential;
    }

    @Transactional
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Transactional
    public Collection<Course> getCourses() {
        return courses;
    }
}
