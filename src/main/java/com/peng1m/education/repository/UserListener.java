package com.peng1m.education.repository;

import com.peng1m.education.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserListener {
    private static final BCryptPasswordEncoder encoder
            = new BCryptPasswordEncoder();

    /**
     * Encode user's password before it is saved into database
     * @param user
     */
    @PrePersist
    @PreUpdate
    public void beforeSave(User user){
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
