package com.peng1m.education.model;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue()
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User student;

    @OneToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public Mark(){}

    public Mark(User student, Exam exam) {
        this.student = student;
        this.exam = exam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
