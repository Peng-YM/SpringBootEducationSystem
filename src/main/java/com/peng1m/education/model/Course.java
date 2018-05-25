package com.peng1m.education.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course extends BaseModel{
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;

    @Column(name = "course_name")
    private String courseName;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Collection<User> teachers;

    public Course(String courseName, String description, List<User> teachers) {
        this.courseName = courseName;
        this.description = description;
        this.teachers = teachers;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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
}
