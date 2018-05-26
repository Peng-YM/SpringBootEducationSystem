package com.peng1m.education.repository;

import com.peng1m.education.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource(
        collectionResourceRel = "profiles",
        path = "profiles"
)
@PreAuthorize("hasRole('USER')")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    void delete(User user);

    @PreAuthorize("hasRole('ADMIN') or profile.user.email == principal.username")
    @Override
    <S extends User> S save(S profile);

    @PostAuthorize("hasRole('ADMIN') or id == returnObject.get().profileId")
    @Override
    Optional<User> findById(Long id);

    @PostAuthorize("hasRole('ADMIN')")
    @Override
    void deleteById(Long id);

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    Iterable<User> findAll();
}
