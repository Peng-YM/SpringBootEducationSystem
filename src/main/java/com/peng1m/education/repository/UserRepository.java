package com.peng1m.education.repository;

import com.peng1m.education.model.User;
import io.swagger.annotations.Api;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@Api(tags = {"user"})
@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users"
)
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Find user by email
     * example usage: GET /users/search/findByEmail?email=someone@somewhere.com
     *
     * @param email user's email
     * @return User
     */
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN') or #email == principal.username")
    Optional<User> findByEmail(@Param("email") String email);

    /**
     * Only admin and the user himself can create/update user.
     *
     * @param user user
     * @param <S>  subtype
     * @return User
     */
    @PreAuthorize("hasRole('ADMIN') or #user.email == principal.username")
    @Override
    <S extends User> S save(S user);

    /**
     * Only admin and the user himself can access profile
     *
     * @param id
     * @return null or user object
     */
    @PostAuthorize("hasAnyRole('ADMIN', 'TEACHER') or returnObject.get().userId == #id")
    @Override
    Optional<User> findById(@Param("id") Long id);

    /**
     * Only admin can delete user
     *
     * @param id userId
     */
    @PostAuthorize("hasRole('ADMIN')")
    @Override
    void deleteById(Long id);

    /**
     * Only admin can access all user list
     *
     * @return List of users
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @Override
    Iterable<User> findAll();
}
