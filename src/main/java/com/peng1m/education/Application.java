package com.peng1m.education;
import com.peng1m.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    private UserRepository userRepository;

    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}
