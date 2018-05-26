package com.peng1m.education.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "exams")
public class Exam extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long examId;

    // many to one
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "exam_name")
    private String examName;

    /**
     * One exam has many marks
     */
    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Collection<Mark> marks;

    public Exam(){}

    public Exam(Course course, String examName) {
        this.course = course;
        this.examName = examName;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

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

    @Transactional
    public Collection<Mark> getMarks() {
        return marks;
    }

    @Transactional
    public void setMarks(Collection<Mark> marks) {
        this.marks = marks;
    }
}
