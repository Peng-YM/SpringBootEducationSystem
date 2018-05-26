package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "courses")
@ToString
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;

    @Column(unique = true) // unique course code
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;
    private String description;

    /**
     * teachers for the courses
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    @JsonIgnore
    private Collection<User> teachers;

    /**
     * students which take the courses
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "course_users",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Collection<User> students;

    public Course() {
    }

    public Course(String courseCode, String courseName, String description) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
    }

    @Transactional
    public Collection<User> getTeachers() {
        return teachers;
    }

    @Transactional
    public void setTeachers(Collection<User> teachers) {
        this.teachers = teachers;
    }

    @Transactional
    public Collection<User> getStudents() {
        return students;
    }

    @Transactional
    public void setStudents(Collection<User> students) {
        this.students = students;
    }
}
