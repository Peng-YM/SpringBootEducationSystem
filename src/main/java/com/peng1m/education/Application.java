package com.peng1m.education;

import com.peng1m.education.model.*;
import com.peng1m.education.repository.CourseRepository;
import com.peng1m.education.repository.ExamRepository;
import com.peng1m.education.repository.MarkRepository;
import com.peng1m.education.repository.UserRepository;
import com.peng1m.education.repository.internal.CredentialRepository;
import com.peng1m.education.repository.internal.RoleRepository;
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
    private RoleRepository roleRepository;
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

//    @PostConstruct
    public void init() {
        Role userRole = roleRepository.save(new Role("ROLE_USER"));
        Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));

        Credential credential = credentialRepository.save(new Credential(
                "11510035@mail.sustc.edu.cn",
                encoder.encode("123456"),
                Arrays.asList(userRole, adminRole)
        ));
        User user = userRepository.save(new User(
                "11510035@mail.sustc.edu.cn",
                "YM",
                "Peng",
                "12345678901",
                credential
        ));

        Credential credential1 = credentialRepository.save(new Credential(
                "11510050@mail.sustc.edu.cn",
                encoder.encode("123456"),
                Arrays.asList(userRole)
        ));

        User user1 = new User(
                "11510050@mail.sustc.edu.cn",
                "GY",
                "Wang",
                "12345678901",
                credential1
        );
        userRepository.save(user1);

        Course course = courseRepository.save(new Course(
                "CS303",
                "Data Structure and Algorithm",
                "Data Structure and Algorithm"
        ));
        course.setTeachers(
                Arrays.asList(user)
        );
        course.setStudents(
                Arrays.asList(user1)
        );
        courseRepository.save(course);
        Exam exam = examRepository.save(new Exam(course, "Mid-term"));
        Mark userMark = markRepository.save(
                new Mark(
                        user, exam, 100
                )
        );

        Mark user1Mark = markRepository.save(
                new Mark(
                        user1, exam, 100
                )
        );
        System.out.println(user.toString());
    }
}
