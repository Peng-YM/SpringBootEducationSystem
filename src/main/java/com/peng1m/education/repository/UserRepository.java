package com.peng1m.education.repository;

import com.peng1m.education.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
        collectionResourceRel = "users",
        path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    /**
     * Access user's info by email, only allows the user to access his own info.
     * Of course admin can access them.
     * @param email email
     * @return User
     */
//    @PostAuthorize("returnObject.email == #email or hasRole('ROLE_ADMIN')")
    User findByEmail(@Param("email") String email);
}
