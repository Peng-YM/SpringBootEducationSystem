package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.peng1m.education.repository.serializers.JsonDateSerializer;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "courses")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;

    @Column(unique = true) // unique course code
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "description", columnDefinition = "varchar(50000)")
    private String description;

    @Column(name = "start")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date start;

    @Column(name = "end")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date end;

    /**
     * users for the courses
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Collection<User> users;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<FileResource> resources;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Exam> exams;

    public Course() {
    }

    public Course(String courseCode, String courseName, String description) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
    }

    @Transactional
    public Collection<User> getUsers() {
        return users;
    }

    @Transactional
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @Transactional
    public Collection<Exam> getExams() {
        return exams;
    }

    @Transactional
    public void setExams(Collection<Exam> exams) {
        this.exams = exams;
    }

    @RestResource
    public long getId() {
        return courseId;
    }

}
