package com.peng1m.education.repository;

import com.peng1m.education.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users")
//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Access user's info by email, only allows the user to access his own info.
     * Of course admin can access them.
     * @param email email
     * @return User
     */
//    @PreAuthorize("hasRole('ADMIN') or #email == principal.username")
    User findByEmail(@Param("email") String email);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    <S extends User> S save(S s);

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    void delete(User user);
}
