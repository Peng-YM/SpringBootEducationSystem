package com.peng1m.education.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Resource that related to some courses.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "file_resources")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FileResource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resource_id")
    private long resourceId;

    /**
     * File name
     */
    @NotNull
    private String name;

    @NotNull
    private String uuid;

    /**
     * File Resource Description
     */
    @NotNull
    @URL
    private String url;

    @NotNull
    private String fileType;

    @NotNull
    @Min(0)
    private long size;
    /**
     * If you want to add a resource to a course, use PUT method to send text/uri-list content
     * Example Usage:
     * curl -u pengym@qq.com:123456 -i -X PUT -H "Content-Type: text/uri-list" -d "http://localhost:8080/courses/58" http://localhost:8080/api/resources/62/course
     * will add resource id 62 to course id 58
     * <p>
     * If you want to delete a resource from a course, Use DELETE
     * Example Usage
     * curl -u pengym@qq.com:123456 -i -X DELETE http://localhost:8080/resource/62/course
     * will delete resource id 62 from course id 58
     */
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public FileResource(@NotNull String uuid, @NotNull String name, @NotNull @URL String url, @NotNull String fileType, @NotNull @Min(0) long size) {
        this.uuid = uuid;
        this.name = name;
        this.url = url;
        this.fileType = fileType;
        this.size = size;
    }
}
