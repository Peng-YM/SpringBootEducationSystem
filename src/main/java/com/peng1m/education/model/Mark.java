package com.peng1m.education.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "marks")
@ToString
public class Mark {
    @Id
    @Column(name = "mark_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long markId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @RestResource(path = "student", rel = "student")
    private User student;

    /**
     * One exam can have many marks.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "exam_id")
    @RestResource(path = "exam", rel = "exam")
    private Exam exam;

    @NotNull
    @Range(min = 0, max = 100) // range validation
    private float score;

    public Mark() {
    }

    public Mark(User student, Exam exam, float score) {
        this.student = student;
        this.exam = exam;
        this.score = score;
    }


    @Transactional
    public User getStudent() {
        return student;
    }

    @Transactional
    public void setStudent(User student) {
        this.student = student;
    }

    @Transactional
    public Exam getExam() {
        return exam;
    }

    @Transactional
    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
