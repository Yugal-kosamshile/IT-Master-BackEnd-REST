package com.online.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.online.course.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
    List<User> findAllByUsername(String username);
    boolean existsByUsername(String username);
//    boolean existsByUsernameAndCourse_Id(String username, Long courseId);
    boolean existsByUsernameAndCourses_Id(String username, Long courseId);
//    void deleteByUsernameAndCourse_Id(String username, Long courseId);
  void deleteByUsernameAndCourses_Id(String username, Long courseId);
}
