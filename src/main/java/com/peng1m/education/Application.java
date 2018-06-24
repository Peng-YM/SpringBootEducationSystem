package com.peng1m.education;

import com.peng1m.education.config.FileStorageProperties;
import com.peng1m.education.config.SecurityUtils;
import com.peng1m.education.model.*;
import com.peng1m.education.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;


@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class Application {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Only for debug usage, insert sample data, will be deleted on production!
     * If you don't want to create sample data, comment the @PostConstruct annotation
     */
    @PostConstruct
    public void init() {
        SecurityUtils.runAsAdmin();
        userRepository.deleteAll();
        courseRepository.deleteAll();
        roleRepository.deleteAll();
        examRepository.deleteAll();
        markRepository.deleteAll();

        Role userRole = roleRepository.save(new Role("ROLE_USER"));
        Role adminRole = roleRepository.save(new Role("ROLE_ADMIN"));
        Role teacherRole = roleRepository.save(new Role("ROLE_TEACHER"));

        User user = userRepository.save(new User(
                "pengym@qq.com",
                encoder.encode("123456"),
                "https://ws1.sinaimg.cn/large/c2dfc71dly1fsm6d1upisj20cs0csdg5.jpg",
                "YM",
                "Peng",
                "12345678901",
                Arrays.asList(adminRole, teacherRole)
        ));

        userRepository.save(user);

        User user1 = new User(
                "wang@qq.com",
                encoder.encode("123456"),
                "https://ws1.sinaimg.cn/large/c2dfc71dly1fsm6d1upisj20cs0csdg5.jpg",
                "GY",
                "Wang",
                "12345678901",
                Arrays.asList(userRole)
        );
        userRepository.save(user1);

        Course course = courseRepository.save(new Course(
                "CS303",
                "Data Structure and Algorithm",
                "Data Structure and Algorithm"
        ));
        course.setUsers(
                Arrays.asList(user, user1)
        );
        courseRepository.save(course);
        Exam exam = examRepository.save(new Exam(course, "Mid-term", new Date()));
        Exam exam2 = examRepository.save(new Exam(course, "Final", new Date()));
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
        SecurityUtils.clear();
    }
}
