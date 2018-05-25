package com.peng1m.education.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long examId;

    // many to many relationship
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_exams",
        joinColumns = @JoinColumn(
                name = "exam_id", referencedColumnName = "exam_id"),
        inverseJoinColumns = @JoinColumn(
                name = "user_id", referencedColumnName = "user_id"
        )
    )
    private Collection<User> students;

    // many to one
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "exam_name")
    private String examName;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    @Transactional
    public Collection<User> getStudents() {
        return students;
    }

    public void setStudents(Collection<User> students) {
        this.students = students;
    }

    @Transactional
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
