package com.peng1m.education.repository;

import com.peng1m.education.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(
        collectionResourceRel = "courses",
        path = "courses")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Course findByCourseCode(String courseCode);
}