package com.peng1m.education.repository;

import com.peng1m.education.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Course list are public resource, all user can access them
 */
@RepositoryRestResource(
        collectionResourceRel = "courses",
        path = "courses")
@PreAuthorize("hasRole('USER')")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Course findByCourseCode(String courseCode);

    /**
     * Only allow admin to edit the course information
     *
     * @param s   course
     * @param <S> Course class
     * @return Course
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    <S extends Course> S save(S s);

    /**
     * Only allow admin to delete the course
     *
     * @param aLong
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void deleteById(Long aLong);
}