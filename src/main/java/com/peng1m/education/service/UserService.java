package com.peng1m.education.service;

import com.peng1m.education.model.Role;
import com.peng1m.education.model.User;
import com.peng1m.education.repository.internal.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepository;

    /**
     * Find user by username in database
     *
     * @param email email
     * @return UserDetail Object
     * @throws UsernameNotFoundException when email does not exist in database
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.debug("User: {} is trying to login", email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            LOGGER.debug("User: {} login failed!", email);
            throw new UsernameNotFoundException("Invalid username or password");
        }
        LOGGER.debug(user.getRoles().toString());
        return new org.springframework.security.core.userdetails.User
                (user.getEmail(), user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
