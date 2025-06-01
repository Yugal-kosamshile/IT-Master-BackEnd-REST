package com.online.course.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(length = 10)
    private Long mobile;
    
    private String email;

    @Column(nullable = false)
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    private Course course;
    
    @ManyToMany
    @JoinTable(
        name = "user_courses",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

}
