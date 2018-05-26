package com.peng1m.education;

import com.peng1m.education.model.Course;
import com.peng1m.education.model.Credential;
import com.peng1m.education.model.Role;
import com.peng1m.education.model.User;
import com.peng1m.education.repository.CourseRepository;
import com.peng1m.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

//    @PostConstruct
    public void init() {
        userRepository.deleteAll();
        courseRepository.deleteAll();

        User user = new User(
                "11510035@mail.sustc.edu.cn",
                "YM",
                "Peng",
                "12345678901"
        );
        Credential credential = new Credential(
                user.getEmail(),
                encoder.encode("123456"),
                Arrays.asList(
                        new Role("ROLE_ADMIN"),
                        new Role("ROLE_USER")
                )
        );
        user.setCredential(credential);
        credential.setUser(user);
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }

        User user1 = new User(
                "11510050@mail.sustc.edu.cn",
                "GY",
                "Wang",
                "12345678901"
        );
        Credential credential1 = new Credential(
                user1.getEmail(),
                encoder.encode("123456"),
                Arrays.asList(
                        new Role("ROLE_USER")
                )
        );

        user1.setCredential(credential1);
        credential1.setUser(user1);
        if (userRepository.findByEmail(user1.getEmail()) == null) {
            userRepository.save(user1);
        }

        Course course = new Course(
                "CS303",
                "Data Structure and Algorithm",
                "Data Structure and Algorithm"
        );
        course.setTeachers(
                Arrays.asList(user)
        );
        course.setStudents(
                Arrays.asList(user1)
        );
        if (courseRepository.findByCourseCode("CS303") == null) {
            courseRepository.save(course);
        }

    }

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
}
