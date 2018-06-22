package com.peng1m.education.repository.handlers;

import com.peng1m.education.model.User;
import com.peng1m.education.repository.RoleRepository;
import com.peng1m.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Encrypt User's password before save it into database
     * @param user new user
     */
    @HandleBeforeCreate
    public void handleUserCreate(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    /**
     * Encrypt User's password before update his password
     * @param user user to update
     */
    @HandleBeforeSave
    public void handleUserSave(User user){
        if (user.getPassword() == null || user.getPassword().trim().length() == 0){
            // the password field is not updated
            User storedUser = userRepository.findById(user.getUserId()).get();
            storedUser.setPassword(storedUser.getPassword());
        }else{
            // password is changed in request
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}
