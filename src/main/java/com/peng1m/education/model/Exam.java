package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.peng1m.education.repository.serializers.JsonDateSerializer;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exams")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long examId;

    // one to one
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "date")
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date date;

    public Exam() {
    }

    public Exam(Course course, String examName, Date date) {
        this.course = course;
        this.examName = examName;
        this.date = date;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    @Transactional
    public Course getCourse() {
        return course;
    }

    @Transactional
    public void setCourse(Course course) {
        this.course = course;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    @RestResource
    public long getId() {
        return examId;
    }
}
