package com.online.course.service;

import com.online.course.model.Course;
import com.online.course.model.User;
import com.online.course.repository.CourseRepository;
import com.online.course.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private CourseRepository courseRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //user registration
    public User register(User user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username already exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //user login
    public boolean login(String username, String rawPassword) throws Exception {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return passwordEncoder.matches(rawPassword, optionalUser.get().getPassword());
        } else {
            throw new Exception("User not found");
        }
    }
//    
//    public List<Set<Course>> getCoursesByUsername(String username) {
//        List<User> users = userRepository.findAllByUsername(username);
//        return users.stream().map(User::getCourses).distinct().toList();
//    }
//
//    public void enrollInCourse(String username, Long courseId) {
//        if (userRepository.existsByUsernameAndCourse_Id(username, courseId)) {
//            throw new RuntimeException("User already enrolled in this course");
//        }
//
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new RuntimeException("Course not found"));
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        User newUserCourse = new User();
//        newUserCourse.setUsername(username);
//        newUserCourse.setPassword(user.getPassword());
//        newUserCourse.setEmail(user.getEmail());
//        newUserCourse.setMobile(user.getMobile());
//        newUserCourse.setCourses(course);
//        userRepository.save(newUserCourse);
//    }
    public void unenrollFromCourse(String username, Long courseId) {
        if (!userRepository.existsByUsernameAndCourses_Id(username, courseId)) {
            throw new RuntimeException("User is not enrolled in this course");
        }
        userRepository.deleteByUsernameAndCourses_Id(username, courseId);
    }
}
